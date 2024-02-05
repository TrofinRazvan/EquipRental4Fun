package com.equiprental.fun.services.equipment;

import com.equiprental.fun.models.dto.EquipmentDTO;

public interface EquipmentService {


    EquipmentDTO createNewEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO getEquipmentDetails(Long equipmentId);
    EquipmentDTO updateEquipment(Long equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(Long equipmentId);
}