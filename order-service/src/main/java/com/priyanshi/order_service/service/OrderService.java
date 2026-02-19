package com.priyanshi.order_service.service;

import com.priyanshi.order_service.common.Payment;
import com.priyanshi.order_service.common.TransactionRequest;
import com.priyanshi.order_service.common.TransactionResponse;
import com.priyanshi.order_service.entity.Order;
import com.priyanshi.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    public OrderRepository repository;

    @Autowired
    public RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        //rest call
        Payment paymentResponse=template.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);
        response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and  ordr placed": "there is a failure in payment api order added to cart";
         repository.save(order);
         return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionID(),response);
    }
}
