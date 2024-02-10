package com.equiprental.fun.controllers;

import com.equiprental.fun.models.dto.EquipmentDTO;
import com.equiprental.fun.models.entity.Rent;
import com.equiprental.fun.services.rent.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/equipment/{equipmentId}/customer/{customerId}")
    public ResponseEntity<EquipmentDTO> rentEquipment(
            @PathVariable Long equipmentId,
            @PathVariable Long customerId,
            @RequestBody Rent rent) {
        rentService.rentEquipment(equipmentId, customerId, rent.getStartDate(), rent.getEndDate());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/calculateRentPrice")
    public ResponseEntity<Double> calculateRentPrice(@RequestBody Rent rentRequest) {
        double totalPrice = rentService.calculateRentPrice(
                rentRequest.getStartDate(),
                rentRequest.getEndDate(),
                rentRequest.getEquipment().getPrice()
        );
        return ResponseEntity.ok(totalPrice);
    }
}