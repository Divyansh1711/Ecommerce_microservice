package com.microservice_ecommerce.productService.exception;

import lombok.Data;

@Data
public class ProductNotFoundCustomException extends  RuntimeException{

    private  String errorCode;

    public ProductNotFoundCustomException(String errorMessage, long errorCode ){
        super(errorMessage);
        this.errorCode=Long.toString(errorCode);
    }
}
