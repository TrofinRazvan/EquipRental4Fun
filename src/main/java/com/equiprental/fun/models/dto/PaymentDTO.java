package com.equiprental.fun.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PaymentDTO implements Serializable {

    @NotBlank(message = "Cannot be 0.")
    private double amount;
    @NotNull(message = "Payment date cannot be null.")
    private LocalDate paymentDate;
    @Pattern(regexp = "^(cash|card)$", message = "Payment method must be either 'cash' or 'card'")
    private String paymentMethod;
}