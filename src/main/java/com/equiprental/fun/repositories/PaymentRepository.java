package com.equiprental.fun.repositories;

import com.equiprental.fun.models.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}