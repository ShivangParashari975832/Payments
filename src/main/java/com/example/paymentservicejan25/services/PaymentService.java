package com.example.paymentservicejan25.services;

import com.example.paymentservicejan25.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(Long orderId, String phoneNumber) {
        //Make a call to Payment Gateway to generate the payment link.
        return paymentGateway.initiatePayment(
                orderId,
                phoneNumber
        );
    }
}
