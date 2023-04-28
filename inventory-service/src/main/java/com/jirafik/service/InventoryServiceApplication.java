package com.jirafik.service;

import com.jirafik.service.entity.Inventory;
import com.jirafik.service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository repository){
//        return args -> {
//            Inventory inventory1 = Inventory.builder()
//                    .skuCode("product 1")
//                    .quantity(13)
//                    .build();
//            Inventory inventory2 = Inventory.builder()
//                    .skuCode("product 2")
//                    .quantity(44)
//                    .build();
//            Inventory inventory3 = Inventory.builder()
//                    .skuCode("product 3")
//                    .quantity(3)
//                    .build();
//
//            repository.save(inventory1);
//            repository.save(inventory2);
//            repository.save(inventory3);
//
//        };
//    }

}
