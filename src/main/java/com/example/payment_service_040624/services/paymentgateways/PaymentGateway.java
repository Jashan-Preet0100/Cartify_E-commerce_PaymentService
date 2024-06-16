package com.example.payment_service_040624.services.paymentgateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    public String createPaymentLink(String orderId, Long amount) throws StripeException;
}
