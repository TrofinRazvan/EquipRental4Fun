package com.equiprental.fun.services.customer;

import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.models.entity.Customer;
import com.equiprental.fun.repositories.CustomerRepository;
import com.equiprental.fun.util.StringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final CustomerServiceValidation customerServiceValidation;
    private final StringService stringService;

    @Autowired
    public CustomerServiceImpl(ObjectMapper objectMapper, CustomerRepository customerRepository, CustomerServiceValidation customerServiceValidation, StringService stringService) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
        this.customerServiceValidation = customerServiceValidation;
        this.stringService = stringService;
    }

    @Override
    @Transactional
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        try {
            customerServiceValidation.checkIfCustomerExists(customerDTO);
            Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
            Customer savedCustomer = customerRepository.save(customer);
            log.info("Customer {} {} has been created.", savedCustomer.getFirstName(), savedCustomer.getLastName());
            return objectMapper.convertValue(savedCustomer, CustomerDTO.class);
        } catch (Exception e) {
            log.error("Error creating new customer", e);
            throw new ServiceException("Error occurred while creating customer: " + e);
        }
    }

    @Override
    public CustomerDTO getCustomerDetails(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException.UserNotFoundException(customerId));
        return objectMapper.convertValue(customer, CustomerDTO.class);
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer updateCustomer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException.UserNotFoundException(customerId));
        customerServiceValidation.checkIfCustomerExists(customerDTO);
        updateCustomerDetails(updateCustomer, customerDTO);
        customerRepository.save(updateCustomer);
        return objectMapper.convertValue(updateCustomer, CustomerDTO.class);
    }

    public void updateCustomerDetails(Customer updatedCustomer, CustomerDTO customerDTO) {
        updatedCustomer.setFirstName(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getFirstName()));
        updatedCustomer.setLastName(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getLastName()));
        updatedCustomer.setCNP(customerDTO.getCNP());
        updatedCustomer.setEmail(customerDTO.getEmail());
        updatedCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        updatedCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        updatedCustomer.setGender(customerDTO.getGender().name());
        updatedCustomer.setStreet(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getStreet()));
        updatedCustomer.setBuildingNumber(customerDTO.getBuildingNumber());
        updatedCustomer.setCity(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getCity()));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer deleteCustomer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException.UserNotFoundException(customerId));
        customerRepository.delete(deleteCustomer);
    }
}