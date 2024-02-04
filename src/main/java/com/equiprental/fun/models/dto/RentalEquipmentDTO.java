package com.equiprental.fun.models.dto;

import com.equiprental.fun.models.entity.EquipmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentalEquipmentDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Equipment type cannot be null.")
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    @NotBlank(message = "Description cannot be blank.")
    private String description;
    @NotNull(message = "Available count cannot be null.")
    private int availableCount;
    @NotNull(message = "Price per day cannot be null.")
    private double pricePerDay;
    @NotNull(message = "Start date cannot be null.")
    @FutureOrPresent(message = "Start date must be in the present or future.")
    private LocalDate startDate;
    @NotNull(message = "End date cannot be null.")
    @FutureOrPresent(message = "End date must be in the present or future.")
    private LocalDate endDate;
}