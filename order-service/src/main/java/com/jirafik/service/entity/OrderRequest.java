package com.jirafik.service.entity;

import com.jirafik.service.dto.OrderLineItemsDto;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}


