package com.equiprental.fun.repositories;

import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.models.entity.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByEquipmentTypeAndBrand(EquipmentType equipmentType, String brand);
}