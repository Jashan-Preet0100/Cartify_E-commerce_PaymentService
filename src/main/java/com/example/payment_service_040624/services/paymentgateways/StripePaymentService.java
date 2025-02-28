package com.example.payment_service_040624.services.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentGateway{

    @Value("${stripe.api.secret}")
    private String stripeApiSecret;

    public String createPaymentLink(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = stripeApiSecret;

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Order Id: " + orderId).build()
                        )
                        .build();

        Price price = Price.create(priceParams);


        String paymentSuccessUrl = "https://www.linkedin.com/in/jashan-preet-singh-1a94a917a/";
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl(paymentSuccessUrl)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        System.out.println(paymentLink.getUrl());
        return paymentLink.getUrl();

    }
}
