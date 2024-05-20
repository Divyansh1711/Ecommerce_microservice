package com.microsService_Project.Order_service.external.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsService_Project.Order_service.exception.CustomException;
import com.microsService_Project.Order_service.external.client.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            ErrorResponse errorResponse=objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
            return new CustomException(errorResponse.getErrorCode(), errorResponse.getErrorMessage());
        } catch (IOException e){
            throw  new CustomException("500","INTERNAL_SERVER_ERROR");
        }
    }
}
