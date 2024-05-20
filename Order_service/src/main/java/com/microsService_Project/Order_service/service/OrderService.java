package com.microsService_Project.Order_service.service;

import com.microsService_Project.Order_service.model.OrderRequest;
import com.microsService_Project.Order_service.model.OrderResponse;

public interface OrderService {
    long add(OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);
}
