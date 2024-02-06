package com.equiprental.fun.controllers;

import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.models.entity.EquipmentType;
import com.equiprental.fun.services.equipment.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createNewEquipment(@RequestBody @Valid EquipmentDTO equipmentDTO) {
        EquipmentDTO createdEquipment = equipmentService.createNewEquipment(equipmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @PutMapping("update/{equipmentId}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long equipmentId, @RequestBody @Valid EquipmentDTO equipmentDTO) {
        EquipmentDTO updatedEquipment = equipmentService.updateEquipment(equipmentId, equipmentDTO);
        return ResponseEntity.ok(updatedEquipment);
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentDTO> getEquipmentDetails(@PathVariable Long equipmentId) {
        EquipmentDTO equipmentDetails = equipmentService.getEquipmentById(equipmentId);
        return ResponseEntity.ok(equipmentDetails);
    }

    @GetMapping("/type/{equipmentType}")
    public ResponseEntity<EquipmentDTO> getAvailableEquipmentByType(@PathVariable EquipmentType equipmentType) {
        EquipmentDTO equipmentByType = equipmentService.getAvailableEquipmentByType(equipmentType);
        return ResponseEntity.ok(equipmentByType);
    }

    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long equipmentId) {
        equipmentService.deleteEquipment(equipmentId);
        return ResponseEntity.noContent().build();
    }
}