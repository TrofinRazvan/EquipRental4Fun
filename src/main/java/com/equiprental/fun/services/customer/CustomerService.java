package com.equiprental.fun.services.customer;

import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.models.dto.login.LoginRequestDto;
import com.equiprental.fun.models.dto.login.RegisterRequestDto;

public interface CustomerService {

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerDetails(Long customerId);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    RegisterRequestDto customerRegisterRequest(RegisterRequestDto customerRegisterRequestDto);

    LoginRequestDto customerLoginRequest(LoginRequestDto customerLoginRequestDto);
}