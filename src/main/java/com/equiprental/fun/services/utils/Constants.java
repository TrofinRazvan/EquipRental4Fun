package com.equiprental.fun.services.utils;

import org.springframework.stereotype.Service;

@Service
public class Constants {

    public static final String PAYPAL_CHECKOUT_ORDERS_URL = "https://api.sandbox.paypal.com/v2/checkout/orders";
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    public static final String PAYMENT_STATUS_COMPLETED = "COMPLETED";
    public static final String PAYPAL_AUTHORIZATION_TOKEN = "Bearer AA";
}