package com.example.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        System.out.println("Order service created");
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("Post Construct");
//    }
//
//    @PreDestroy
//    public void cleanUp() {
//        System.out.println("Pre Destroy");
//    }
}