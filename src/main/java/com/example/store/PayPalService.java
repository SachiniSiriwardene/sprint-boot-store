package com.example.store;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service("paypal")
public class PayPalService implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("Paypal");
        System.out.println("Amount: "+ amount);
    }
}
