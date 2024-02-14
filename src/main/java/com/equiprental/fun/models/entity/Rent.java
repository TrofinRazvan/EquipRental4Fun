package com.equiprental.fun.models.entity;

import com.equiprental.fun.util.RentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "rental_price")
    private Double rentalPrice;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RentStatus rentStatus;
}