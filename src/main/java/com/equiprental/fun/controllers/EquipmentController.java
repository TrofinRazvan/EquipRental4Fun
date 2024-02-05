package com.equiprental.fun.controllers;

import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.services.equipment.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createNewEquipment(@RequestBody @Valid EquipmentDTO equipmentDTO) {
        EquipmentDTO createdEquipment = equipmentService.createNewEquipment(equipmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }
}