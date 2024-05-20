package com.microService_project.paymentService.entity;

import com.microService_project.paymentService.model.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Entity
@Data
@Table(name = "TRANSACTION_DETAILS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ORDER_ID")
    private long orderId;
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Column(name = "PAYMENT_DATE")
    private Instant paymentDate;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name = "AMOUNT")
    private long amount;
}
