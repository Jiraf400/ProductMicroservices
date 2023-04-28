package com.jirafik.service.service;

import com.jirafik.service.dto.InventoryResponse;
import com.jirafik.service.dto.OrderLineItemsDto;
import com.jirafik.service.entity.Order;
import com.jirafik.service.entity.OrderLineItems;
import com.jirafik.service.entity.OrderRequest;
import com.jirafik.service.repository.OrderRepository;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository repository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;

    public String placeOrder(OrderRequest orderRequest) {
        log.info("--------------------------------Start method placeOrder");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setLineItems(orderLineItems);

        List<String> skuCodeList = order.getLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        log.info("--------------------------------defined skuCodeList collection: {}", skuCodeList);

        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())) {

            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            log.info("--------------------------------defined InventoryResponse collection: {}",
                    Arrays.toString(inventoryResponseArray));

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock && inventoryResponseArray.length != 0) {
                repository.save(order);
                return "Order placed successfully";
            } else throw new IllegalArgumentException("Product is not in stock. Try again later");

        } finally {
            inventoryServiceLookup.end();
        }


    }

    private OrderLineItems mapToDto(OrderLineItemsDto dto) {
        return OrderLineItems.builder()
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .skuCode(dto.getSkuCode())
                .build();
    }


}





