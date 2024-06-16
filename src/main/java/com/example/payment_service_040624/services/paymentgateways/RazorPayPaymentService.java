package com.example.payment_service_040624.services.paymentgateways;

import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentGateway{

    public String createPaymentLink(String orderId, Long amount){
        return null;
    }
}
