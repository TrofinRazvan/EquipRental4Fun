package com.equiprental.fun.services.customer;

import com.equiprental.fun.exceptions.AlreadyExistException;
import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.models.entity.Customer;
import com.equiprental.fun.repositories.CustomerRepository;
import com.equiprental.fun.util.UserRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerServiceValidation {

    private final CustomerRepository customerRepository;

    public CustomerServiceValidation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void checkIfCustomerExists(CustomerDTO customerDTO) {
        Optional<Customer> CnpCustomer = customerRepository.findByCNP(customerDTO.getCNP());
        if (CnpCustomer.isPresent()) {
            throw new AlreadyExistException.CNPAlreadyExistException(customerDTO.getCNP(), UserRole.USER);
        }
        Optional<Customer> emailCustomer = customerRepository.findByEmail(customerDTO.getEmail());
        if (emailCustomer.isPresent()) {
            throw new AlreadyExistException.EmailAddressAlreadyExistException(customerDTO.getEmail(), UserRole.USER);
        }
        Optional<Customer> phoneNumberCustomer = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
        if (phoneNumberCustomer.isPresent()) {
            throw new AlreadyExistException.PhoneNumberAlreadyExistException(customerDTO.getPhoneNumber(), UserRole.USER);
        }
    }
}