package com.equiprental.fun.repositories;

import com.equiprental.fun.models.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findByCustomerId(Long customerId);
}