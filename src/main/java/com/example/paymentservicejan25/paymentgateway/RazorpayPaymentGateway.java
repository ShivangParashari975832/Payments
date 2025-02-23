package com.example.paymentservicejan25.paymentgateway;

import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {
    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        return null;
    }
}
