package com.microService_project.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private long paymentId;
    private String status;
    private String paymentMode;
    private long amount;
    private Instant paymentDate;
    private long orderId;
}
