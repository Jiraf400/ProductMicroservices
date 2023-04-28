package com.jirafik.service.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_inventory")
public class Inventory  {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}








