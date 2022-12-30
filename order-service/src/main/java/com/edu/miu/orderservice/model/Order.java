package com.edu.miu.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Order_Id")
    private List<OrderLineItems> orderLineItemsList;
}
