package com.equiprental.fun.repositories;

import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.util.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByEquipmentType(EquipmentType equipmentType);
    Optional<Equipment> findByBrand(String brand);
}