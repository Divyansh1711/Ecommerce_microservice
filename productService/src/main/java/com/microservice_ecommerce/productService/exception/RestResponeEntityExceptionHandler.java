package com.microservice_ecommerce.productService.exception;

import com.microservice_ecommerce.productService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponeEntityExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> productIdNotFound(ProductServiceCustomException productNotFoundCustomException){
       com.microservice_ecommerce.productService.model.ErrorResponse errorResponse= new com.microservice_ecommerce.productService.model.ErrorResponse().builder().errorMessage(productNotFoundCustomException.getMessage()).errorCode(productNotFoundCustomException.getErrorCode()).build();
         return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
