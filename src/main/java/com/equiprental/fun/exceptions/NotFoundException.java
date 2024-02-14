package com.equiprental.fun.exceptions;

import com.equiprental.fun.models.entity.EquipmentType;
import com.equiprental.fun.util.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

public class NotFoundException {

    @Slf4j
    public static class UserNotFoundException extends AbstractAppException {
        public UserNotFoundException(Long userId) {
            super("User with ID: " + userId + " has not been found.");
        }

        public UserNotFoundException(UserRole userRole) {
            super(StringUtils.capitalize(userRole.getName()) + " does not exist in the system.");
        }
    }

    public static class EquipmentNotFoundException extends AbstractAppException {
        public EquipmentNotFoundException(Long equipmentId) {
            super("No ski type equipment was found in your search query.");
        }
    }

    public static class EquipmentTypeNotFoundException extends AbstractAppException {
        public EquipmentTypeNotFoundException(EquipmentType equipmentType) {
            super("The type of ski type equipment selected does not exist in the system.");
        }
    }

    public static class EquipmentBrandNotFoundException extends AbstractAppException {
        public EquipmentBrandNotFoundException(String brand) {
            super("The selected ski type brand does not exist in the system.");
        }
    }

    public static class NotAvailableException extends AbstractAppException {
        public NotAvailableException(String message) {
            super("Equipment not available for rent.");
        }
    }

    public static class RentNotFoundException extends AbstractAppException {
        public RentNotFoundException() {
            super("The rental you are looking for does not exist in the system or has been deleted.");
        }
    }
}