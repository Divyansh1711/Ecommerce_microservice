package com.microService_project.paymentService.service;

import com.microService_project.paymentService.model.PaymentRequest;
import com.microService_project.paymentService.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentById(long id);
}
