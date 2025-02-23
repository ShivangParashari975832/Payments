package com.example.paymentservicejan25.controller;

import com.example.paymentservicejan25.dtos.InitiatePaymentRequestDto;
import com.example.paymentservicejan25.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/initiate/{orderId}")
    public String initiatePayment(@PathVariable("orderId") String orderId) {
        return paymentService.initiatePayment(
                Long.valueOf(orderId),
                "872456784"
        );
    }
}
