package com.edu.miu.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Long Id;
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsListDto;
}
