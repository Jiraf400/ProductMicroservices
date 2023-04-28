package com.jirafik.service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = ALL)
    private List<OrderLineItems> lineItems;
}





