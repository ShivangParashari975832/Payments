package com.example.paymentservicejan25.paymentgateway;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String initiatePayment(Long orderId, String phoneNumber);
}
