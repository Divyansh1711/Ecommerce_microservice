package com.microsService_Project.Order_service.config;

import com.microsService_Project.Order_service.exception.CustomException;
import com.microsService_Project.Order_service.external.client.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();}
}
