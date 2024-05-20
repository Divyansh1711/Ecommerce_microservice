package com.microsService_Project.Order_service.model;

import com.microsService_Project.Order_service.external.client.response.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant OrderDate;
    private String oderStatus;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public  static class ProductDetails {
        private String productName;
        private long productId;
        private long price;
        private long quantity;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public  static class PaymentDetails {
        private String paymentMode;
        private long paymentId;
        private String status;
        private Instant paymentDate;
    }
}
