package com.equiprental.fun.exceptions;

import com.equiprental.fun.models.entity.Rent;
import lombok.extern.slf4j.Slf4j;

public class RentException {

    @Slf4j
    public static class RentHasReturnException extends AbstractAppException {
        public RentHasReturnException(Rent rent) {
            super("It is not possible to delete a rental with a rented status.");
            log.warn("Attempt to remove rent with return status. Rent data: {}", rent);
        }
    }

    public static class RentAlreadyReturnedException extends AbstractAppException {
        public RentAlreadyReturnedException(Rent rent) {
            super("Rent with ID " + rent.getId() + " has already been returned.");
        }
    }
}