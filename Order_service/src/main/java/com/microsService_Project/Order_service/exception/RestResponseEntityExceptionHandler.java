package com.microsService_Project.Order_service.exception;

import com.microsService_Project.Order_service.external.client.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> productIdNotFound(CustomException productNotFoundCustomException) {
        ErrorResponse errorResponse = new ErrorResponse().builder().errorMessage(productNotFoundCustomException.getMessage()).errorCode(productNotFoundCustomException.getErrorCode()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

