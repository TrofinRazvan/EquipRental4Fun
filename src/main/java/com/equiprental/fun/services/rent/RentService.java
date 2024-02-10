package com.equiprental.fun.services.rent;

import java.time.LocalDate;

public interface RentService {

    void rentEquipment(Long equipmentId, Long customerId, LocalDate startDate, LocalDate endDate);
    double calculateRentPrice(LocalDate startDate, LocalDate endDate, double price);
}