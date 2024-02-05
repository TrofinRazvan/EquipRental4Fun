package com.equiprental.fun.services.equipment;

import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.repositories.EquipmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final ObjectMapper objectMapper;
    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(ObjectMapper objectMapper, EquipmentRepository equipmentRepository) {
        this.objectMapper = objectMapper;
        this.equipmentRepository = equipmentRepository;
    }
    @Override
    public EquipmentDTO createNewEquipment(EquipmentDTO equipmentDTO) {
        Optional<Equipment> existingEquipmentOptional = equipmentRepository.findByEquipmentTypeAndBrand(equipmentDTO.getEquipmentType(), equipmentDTO.getBrand());
        Equipment existingEquipment = existingEquipmentOptional.orElse(null);
        if (existingEquipment != null) {
            existingEquipment.setAvailableCount(existingEquipment.getAvailableCount() + 1);
            equipmentRepository.save(existingEquipment);
            log.info("Incremented available count for existing equipment with ID {}.", existingEquipment.getId());
            return objectMapper.convertValue(existingEquipment, EquipmentDTO.class);
        } else {
            Equipment newEquipmentEntity = objectMapper.convertValue(equipmentDTO, Equipment.class);
            newEquipmentEntity.setAvailableCount(1);
            Equipment savedEquipment = equipmentRepository.save(newEquipmentEntity);
            log.info("Equipment with ID {} has been created.", savedEquipment.getId());
            return objectMapper.convertValue(savedEquipment, EquipmentDTO.class);
        }
    }

    @Override
    public EquipmentDTO getEquipmentDetails(Long equipmentId) {
        return null;
    }

    @Override
    public EquipmentDTO updateEquipment(Long equipmentId, EquipmentDTO equipmentDTO) {
        return null;
    }

    @Override
    public void deleteEquipment(Long equipmentId) {

    }
}