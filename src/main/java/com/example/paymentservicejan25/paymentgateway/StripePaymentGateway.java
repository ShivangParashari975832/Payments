package com.example.paymentservicejan25.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key}")
    private String apiKey;

    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        Stripe.apiKey = apiKey;

        // 10.25 => 10.25 * 100 = 1025
        // 10.00 => 1000

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("iPhone charger").build()
                        )
                        .build();

        Price price = null;
        try {
            price = Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(10L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("http://scaler.com")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(paymentLinkCreateParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return paymentLink.getUrl();
    }
}
