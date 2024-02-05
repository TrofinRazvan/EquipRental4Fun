package com.equiprental.fun.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RentDTO implements Serializable {

    @NotBlank(message = "Price must be specified.")
    private String pricePerDay;
    @NotNull(message = "Must be specified start date.")
    private LocalDate startDate;
    @NotNull(message = "Must be specified end date.")
    private LocalDate endDate;
}