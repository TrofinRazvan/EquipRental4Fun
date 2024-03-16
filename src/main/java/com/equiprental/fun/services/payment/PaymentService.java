package com.equiprental.fun.services.payment;

import com.equiprental.fun.models.entity.Payment;

public interface PaymentService {

    Payment createPayment(Long customerId);
}