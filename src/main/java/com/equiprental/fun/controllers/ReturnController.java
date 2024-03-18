package com.equiprental.fun.controllers;

import com.equiprental.fun.services.returnequipment.ReturnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class ReturnController {

    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping("/{rentId}")
    public ResponseEntity<String> returnEquipment(@PathVariable Long rentId) {
        returnService.returnEquipment(rentId);
        return ResponseEntity.ok("Equipment returned successfully for rent ID: " + rentId);
    }
}