package com.edu.miu.orderservice.service;

import com.edu.miu.orderservice.dto.OrderLineItemsDto;
import com.edu.miu.orderservice.dto.OrderRequest;
import com.edu.miu.orderservice.dto.OrderResponse;
import com.edu.miu.orderservice.model.Order;
import com.edu.miu.orderservice.model.OrderLineItems;
import com.edu.miu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
       Order order = new Order();
       order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
               .map(this::mapToDomain)
               .toList();

       order.setOrderLineItemsList(orderLineItemsList);

       orderRepository.save(order);
    }

    private OrderLineItems mapToDomain(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderNumber(order.getOrderNumber());

        List<OrderLineItems> orderLineItemsList = order.getOrderLineItemsList();
        List<OrderLineItemsDto> orderLineItemsDtoList = orderLineItemsList.stream()
                .map(this::mapToDto)
                .toList();

        orderResponse.setOrderLineItemsListDto(orderLineItemsDtoList);
        return orderResponse;
    }

    private OrderLineItemsDto mapToDto(OrderLineItems orderLineItems) {
        OrderLineItemsDto orderLineItemsDto = new OrderLineItemsDto();
        orderLineItemsDto.setId(orderLineItems.getId());
        orderLineItemsDto.setSkuCode(orderLineItems.getSkuCode());
        orderLineItemsDto.setQuantity(orderLineItems.getQuantity());
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        return orderLineItemsDto;
    }
}
