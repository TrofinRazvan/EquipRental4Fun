package com.equiprental.fun.services.equipment;

import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.util.EquipmentType;

public interface EquipmentService {


    EquipmentDTO createNewEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO updateEquipment(Long equipmentId, EquipmentDTO equipmentDTO);
    EquipmentDTO getEquipmentById(Long equipmentId);
    EquipmentDTO getAvailableEquipmentByType(EquipmentType equipmentType);
    void deleteEquipment(Long equipmentId);
}