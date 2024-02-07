package com.equiprental.fun.models.dto;

import com.equiprental.fun.models.entity.EquipmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class EquipmentDTO implements Serializable {

    @NotNull(message = "Equipment type cannot be null.")
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    @NotBlank(message = "Price must be specified.")
    private String price;
    @NotBlank(message = "Equipment brand cannot be blank.")
    private String brand;
    @NotBlank(message = "Description cannot be blank.")
    private String description;
    @NotNull(message = "Available count cannot be null.")
    private int availableCount;
}