package com.microService_project.paymentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.web.ErrorResponse.builder;

@ControllerAdvice
public class RestEntityExceptionHandler {
//      @ExceptionHandler(PaymentCustomException.class)
//      public ResponseEntity<ErrorResponse> paymentIdInvalid(PaymentCustomException paymentCustomException){
//          ErrorResponse errorResponse= new ErrorResponse() {
//              @Override
//              public HttpStatusCode getStatusCode() {
//                  return paymentCustomException.getErrorCode();
//              }
//
//              @Override
//              public ProblemDetail getBody() {
//                  ProblemDetail problemDetail= new ProblemDetail();
//                  return ProblemDetail;
//              }
//          }
//          return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//      }
}
