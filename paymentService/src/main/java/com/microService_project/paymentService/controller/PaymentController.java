package com.microService_project.paymentService.controller;

import com.microService_project.paymentService.model.PaymentRequest;
import com.microService_project.paymentService.model.PaymentResponse;
import com.microService_project.paymentService.service.PaymentService;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<PaymentResponse>  getPaymentById(@PathVariable long id){
        log.info("Method called for getting payment details");
        PaymentResponse paymentResponse = paymentService.getPaymentById(id);

        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }
}
