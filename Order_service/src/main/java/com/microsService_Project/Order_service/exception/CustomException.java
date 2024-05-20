package com.microsService_Project.Order_service.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    String errorCode;

    public CustomException(String errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode=errorCode;
    }


}
