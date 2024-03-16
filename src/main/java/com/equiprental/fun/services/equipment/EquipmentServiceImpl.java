package com.equiprental.fun.services.equipment;

import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.util.EquipmentType;
import com.equiprental.fun.repositories.EquipmentRepository;
import com.equiprental.fun.services.utils.StringUtilsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ObjectMapper objectMapper;
    private final EquipmentServiceValidation equipmentServiceValidation;
    private final StringUtilsService stringService;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, ObjectMapper objectMapper, EquipmentServiceValidation equipmentServiceValidation, StringUtilsService stringService) {
        this.equipmentRepository = equipmentRepository;
        this.objectMapper = objectMapper;
        this.equipmentServiceValidation = equipmentServiceValidation;
        this.stringService = stringService;
    }

    @Override
    public EquipmentDTO createNewEquipment(EquipmentDTO equipmentDTO) {
        Optional<Equipment> existingEquipmentOptional = equipmentRepository.findByEquipmentType(equipmentDTO.getEquipmentType());
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
    @Transactional
    public EquipmentDTO getEquipmentById(Long equipmentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new NotFoundException.EquipmentNotFoundException(equipmentId));
        return objectMapper.convertValue(equipment, EquipmentDTO.class);
    }

    @Override
    public EquipmentDTO getAvailableEquipmentByType(EquipmentType equipmentType) {
        Equipment equipment = equipmentRepository.findByEquipmentType(equipmentType).orElseThrow(() -> new NotFoundException.EquipmentTypeNotFoundException(equipmentType));
        return objectMapper.convertValue(equipment,EquipmentDTO.class);
    }

    @Override
    public EquipmentDTO updateEquipment(Long equipmentId, EquipmentDTO equipmentDTO) {
        Equipment updateEquipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new NotFoundException.EquipmentNotFoundException(equipmentId));
        equipmentServiceValidation.checkIfEquipmentExists(equipmentDTO);
        updateEquipmentDetails(updateEquipment, equipmentDTO);
        equipmentRepository.save(updateEquipment);
        return objectMapper.convertValue(updateEquipment, EquipmentDTO.class);
    }

    public void updateEquipmentDetails(Equipment updatedEquipment, EquipmentDTO equipmentDTO) {
        updatedEquipment.setEquipmentType(equipmentDTO.getEquipmentType());
        updatedEquipment.setBrand(equipmentDTO.getBrand());
        updatedEquipment.setDescription(stringService.capitalizeAndRemoveWhiteSpaces(equipmentDTO.getDescription()));
        updatedEquipment.setAvailableCount(equipmentDTO.getAvailableCount());
    }

    @Override
    public void deleteEquipment(Long equipmentId) {
        Equipment deleteEquipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new NotFoundException.EquipmentNotFoundException(equipmentId));
        equipmentRepository.delete(deleteEquipment);
    }
}