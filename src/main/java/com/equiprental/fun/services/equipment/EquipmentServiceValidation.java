package com.equiprental.fun.services.equipment;

import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.repositories.EquipmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EquipmentServiceValidation {

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceValidation(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public void checkIfEquipmentExists(EquipmentDTO equipmentDTO) {
        Optional<Equipment> equipmentType = equipmentRepository.findByEquipmentType(equipmentDTO.getEquipmentType());
        if (equipmentType.isPresent()) {
            throw new NotFoundException.EquipmentTypeNotFoundException(equipmentDTO.getEquipmentType());
        }
        Optional<Equipment> equipmentBrand = equipmentRepository.findByBrand(equipmentDTO.getBrand());
        if (equipmentType.isPresent()) {
            throw new NotFoundException.EquipmentBrandNotFoundException(equipmentDTO.getBrand());
        }
    }
}