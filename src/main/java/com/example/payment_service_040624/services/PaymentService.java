package com.example.payment_service_040624.services;

import com.example.payment_service_040624.services.paymentgateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(@Qualifier("stripe") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }


    public String generatePaymentLink(String orderId, Long amount) throws StripeException {
        return paymentGateway.createPaymentLink(orderId,amount);
    }
}
