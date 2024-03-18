package com.equiprental.fun.controllers;

import com.equiprental.fun.models.dto.CustomerDTO;
import com.equiprental.fun.models.dto.login.LoginRequestDto;
import com.equiprental.fun.models.dto.login.RegisterRequestDto;
import com.equiprental.fun.services.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

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

    @GetMapping("/{customerId}/rentedEquipment")
    public ResponseEntity<List<Map<String, Object>>> getRentedEquipmentForCustomer(@PathVariable Long customerId) {
        List<Map<String, Object>> rentedEquipment = customerService.getRentedEquipmentForCustomer(customerId);
        return ResponseEntity.ok(rentedEquipment);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequestDto> registerNewCustomer(@RequestBody @Valid RegisterRequestDto customerRegisterRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.customerRegisterRequest(customerRegisterRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRequestDto> customerLogin(@RequestBody @Valid LoginRequestDto customerLoginRequestDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.customerLoginRequest(customerLoginRequestDto));
    }
}