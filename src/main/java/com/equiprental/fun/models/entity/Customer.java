package com.equiprental.fun.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "cnp")
    private String cnp;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "gender")
    private String gender;
    @Column(name = "street")
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "account_creation_date")
    private LocalDate accountCreationDate;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Rent> rents;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> payments;
}