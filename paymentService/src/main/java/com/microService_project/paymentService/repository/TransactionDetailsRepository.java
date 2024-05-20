package com.microService_project.paymentService.repository;

import com.microService_project.paymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
    TransactionDetails findByorderId(long id);

}
