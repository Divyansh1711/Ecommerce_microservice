package com.microService_project.paymentService.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentCustomException extends RuntimeException {
    private String errorCode;
    public PaymentCustomException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode=errorCode;
    }
}
