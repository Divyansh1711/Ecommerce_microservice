package com.microService_project.paymentService.service;

import com.microService_project.paymentService.entity.TransactionDetails;
import com.microService_project.paymentService.exception.PaymentCustomException;
import com.microService_project.paymentService.model.PaymentMode;
import com.microService_project.paymentService.model.PaymentRequest;
import com.microService_project.paymentService.model.PaymentResponse;
import com.microService_project.paymentService.repository.TransactionDetailsRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;


    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);
        TransactionDetails transactionDetails= TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Payment completed Successfully: {} ",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentById(long id) {
        log.info("Fetching paymentDetails by payment id: {}",id);

        TransactionDetails transactionDetails
                = transactionDetailsRepository.findByorderId(id);

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .status(transactionDetails.getPaymentStatus())
                .paymentDate(transactionDetails.getPaymentDate())
                .amount(transactionDetails.getAmount())
                .orderId(transactionDetails.getOrderId())
                .paymentMode(transactionDetails.getPaymentMode())
                .build();

        log.info("Fetched paymentDetails by payment id: {}",id);

        return paymentResponse;
    }
}
