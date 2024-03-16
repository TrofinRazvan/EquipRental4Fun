package com.equiprental.fun.models.entity;

import com.equiprental.fun.util.EquipmentType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type")
    private EquipmentType equipmentType;
    @Column(name = "price")
    private Double price;
    @Column(name = "brand")
    private String brand;
    @Column(name = "description")
    private String description;
    @Column(name = "available_count")
    private int availableCount;
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Rent> rents;
}