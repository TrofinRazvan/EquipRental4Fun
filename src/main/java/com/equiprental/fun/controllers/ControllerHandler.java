package com.equiprental.fun.controllers;

import com.equiprental.fun.exceptions.AlreadyExistException;
import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.exceptions.RentException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(NotFoundException.UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException.UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @ExceptionHandler({
            AlreadyExistException.CnpAlreadyExistException.class,
            AlreadyExistException.EmailAddressAlreadyExistException.class,
            AlreadyExistException.PhoneNumberAlreadyExistException.class
    })
    public ResponseEntity<Object> handleAlreadyExistException(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource already exits.");
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
    }

    @ExceptionHandler(NotFoundException.RentNotFoundException.class)
    public ResponseEntity<Object> handleRentNotFoundException(NotFoundException.RentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rent not found.");
    }

    @ExceptionHandler(RentException.RentHasReturnException.class)
    public ResponseEntity<Object> handleRentHasReturnException(RentException.RentHasReturnException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rent has already been returned.");
    }
}