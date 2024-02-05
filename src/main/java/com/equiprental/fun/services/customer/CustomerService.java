package com.equiprental.fun.services.customer;

import com.equiprental.fun.models.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerDetails(Long customerId);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}