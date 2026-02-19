package com.priyanshi.payment_service.service;

import com.priyanshi.payment_service.entity.Payment;
import com.priyanshi.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionID(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public String paymentProcessing(){
        //api shuld be third party payment gateway
        return new Random().nextBoolean()?"success":"false";
    }

}
