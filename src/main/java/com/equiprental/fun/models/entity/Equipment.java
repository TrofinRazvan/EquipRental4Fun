package com.equiprental.fun.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rental_equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type")
    private EquipmentType equipmentType;
    @Column(name = "description")
    private String description;
    @Column(name = "available_count")
    private int availableCount;
    @Column(name = "price_per_day")
    private double pricePerDay;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}