package com.equiprental.fun.services.returnequipment;

import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.exceptions.RentException;
import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.models.entity.Rent;
import com.equiprental.fun.repositories.EquipmentRepository;
import com.equiprental.fun.repositories.RentRepository;
import com.equiprental.fun.util.RentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReturnServiceImpl implements ReturnService {

    private final RentRepository rentRepository;
    private final EquipmentRepository equipmentRepository;

    public ReturnServiceImpl(RentRepository rentRepository, EquipmentRepository equipmentRepository) {
        this.rentRepository = rentRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional
    public void returnEquipment(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new NotFoundException.RentNotFoundException(rentId));
        if (rent.getRentStatus() == RentStatus.RETURNED) {
            throw new RentException.RentAlreadyReturnedException(rent);
        }
        rent.setRentStatus(RentStatus.RETURNED);
        rentRepository.save(rent);
        Equipment equipment = rent.getEquipment();
        equipment.setAvailableCount(equipment.getAvailableCount() + 1);
        equipmentRepository.save(equipment);
    }
}