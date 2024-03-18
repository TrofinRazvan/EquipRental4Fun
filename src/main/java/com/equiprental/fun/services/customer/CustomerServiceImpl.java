package com.equiprental.fun.services.customer;

import com.equiprental.fun.exceptions.AlreadyExistException;
import com.equiprental.fun.exceptions.CredentialException;
import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.models.entity.Customer;
import com.equiprental.fun.models.dto.login.LoginRequestDto;
import com.equiprental.fun.models.dto.login.RegisterRequestDto;
import com.equiprental.fun.models.entity.Equipment;
import com.equiprental.fun.models.entity.Rent;
import com.equiprental.fun.repositories.CustomerRepository;
import com.equiprental.fun.repositories.EquipmentRepository;
import com.equiprental.fun.repositories.RentRepository;
import com.equiprental.fun.services.utils.StringUtilsService;
import com.equiprental.fun.util.RentStatus;
import com.equiprental.fun.util.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final CustomerServiceValidation customerServiceValidation;
    private final StringUtilsService stringService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerServiceImpl(ObjectMapper objectMapper, CustomerRepository customerRepository,
                               CustomerServiceValidation customerServiceValidation, StringUtilsService stringService,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
        this.customerServiceValidation = customerServiceValidation;
        this.stringService = stringService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        updatedCustomer.setCnp(customerDTO.getCnp());
        updatedCustomer.setEmail(customerDTO.getEmail());
        updatedCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        updatedCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        updatedCustomer.setGender(customerDTO.getGender().name());
        updatedCustomer.setStreet(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getStreet()));
        updatedCustomer.setBuildingNumber(customerDTO.getBuildingNumber());
        updatedCustomer.setCity(stringService.capitalizeAndRemoveWhiteSpaces(customerDTO.getCity()));
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer deleteCustomer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException.UserNotFoundException(customerId));
        customerRepository.delete(deleteCustomer);
    }

    @Override
    @Transactional
    public RegisterRequestDto customerRegisterRequest(RegisterRequestDto customerRegisterRequestDto) {
        customerServiceValidation.validateCustomerNotAlreadyRegistered(customerRegisterRequestDto);
        Customer customer = objectMapper.convertValue(customerRegisterRequestDto, Customer.class);
        setCustomerDetails(customerRegisterRequestDto, customer);
        customerRepository.save(customer);
        return objectMapper.convertValue(customer, RegisterRequestDto.class);
    }

    private void setCustomerDetails(RegisterRequestDto customerRegisterRequestDto, Customer customer) {
        customer.setAccountCreationDate(LocalDate.now());
        customer.setEmail(customerRegisterRequestDto.getEmail());
        customer.setCity(customerRegisterRequestDto.getCity());
        customer.setGender(customerRegisterRequestDto.getGender().name());
        customer.setFirstName(stringService.capitalizeAndRemoveWhiteSpaces(customerRegisterRequestDto.getFirstName()));
        customer.setLastName(stringService.capitalizeAndRemoveWhiteSpaces(customerRegisterRequestDto.getLastName()));
        String encryptedPassword = bCryptPasswordEncoder.encode(customerRegisterRequestDto.getPassword());
        customer.setPassword(encryptedPassword);
    }

    @Override
    public LoginRequestDto customerLoginRequest(LoginRequestDto customerLoginRequestDto) {
        Optional<Customer> customerLoginOptional = customerRepository.findByEmail(customerLoginRequestDto.getEmail());
        Customer customerLogin = customerLoginOptional.orElseThrow(() -> new NotFoundException.UserNotFoundException(UserRole.USER));
        customerServiceValidation.validateCustomerNotFound(customerLogin);
        if (checkCustomerPassword(customerLoginRequestDto, customerLogin) && checkCustomerEmail(customerLoginRequestDto, customerLogin)) {
            return customerLoginRequestDto;
        } else {
            throw new CredentialException.PasswordException("Incorrect password, please try again!");
        }
    }

    private boolean checkCustomerPassword(LoginRequestDto customerLoginRequestDto, Customer customerLogin) {
        return new BCryptPasswordEncoder().matches(customerLoginRequestDto.getPassword(), customerLogin.getPassword());
    }

    private boolean checkCustomerEmail(LoginRequestDto customerLoginRequestDto, Customer customerLogin) {
        return customerLoginRequestDto.getEmail().equals(customerLogin.getEmail());
    }
}