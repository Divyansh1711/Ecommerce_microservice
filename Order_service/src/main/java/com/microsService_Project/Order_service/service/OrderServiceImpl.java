package com.microsService_Project.Order_service.service;

import com.microsService_Project.Order_service.entity.Order;
import com.microsService_Project.Order_service.model.OrderRequest;
import com.microsService_Project.Order_service.respository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public long add(OrderRequest orderRequest) {
        log.info("placing a order with id: ");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderStatus("Created")
                .OrderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .build();
        orderRepository.save(order);
        log.info("order created succesfully with order id ", order.getId());
        return order.getId();
    }
}
