package com.equiprental.fun.models.dto;

import com.equiprental.fun.util.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Data
public class PaymentDTO implements Serializable {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment currency must be specified.")
    private Currency currency;
    @Pattern(regexp = "^(cash|card)$", message = "Payment method must be either 'cash' or 'card'")
    private String paymentMethod;
}