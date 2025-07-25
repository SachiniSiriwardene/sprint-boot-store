package com.example.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {

    @Value("${stripe.apiUrl}")
    private String apiUrl;

    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe");
        System.out.println(apiUrl);
        System.out.println("Amount: "+ amount);
    }
}
