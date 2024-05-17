package com.microsService_Project.Order_service.service;

import com.microsService_Project.Order_service.model.OrderRequest;

public interface OrderService {
    long add(OrderRequest orderRequest);
}
