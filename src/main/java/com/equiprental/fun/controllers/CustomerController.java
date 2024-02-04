package com.equiprental.fun.controllers;

import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.services.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable Long customerId) {
        CustomerDTO customerDetails = customerService.getCustomerDetails(customerId);
        return ResponseEntity.ok(customerDetails);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
