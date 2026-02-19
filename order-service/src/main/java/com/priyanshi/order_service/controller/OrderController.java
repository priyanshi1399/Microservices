package com.priyanshi.order_service.controller;

import com.priyanshi.order_service.common.Payment;
import com.priyanshi.order_service.common.TransactionRequest;
import com.priyanshi.order_service.common.TransactionResponse;
import com.priyanshi.order_service.entity.Order;
import com.priyanshi.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);
    }
}
