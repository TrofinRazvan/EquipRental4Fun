package com.equiprental.fun.controllers;

import com.equiprental.fun.services.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/payments")
public class PaymentController {

   private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam("customerId") Long customerId) {
        paymentService.createPayment(customerId);
        return ResponseEntity.ok("Order created successfully");
    }
}