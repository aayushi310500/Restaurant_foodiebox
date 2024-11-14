package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.dto.CustomerRequest;
import com.restaurant.restaurant.dto.CustomerResponse;
import com.restaurant.restaurant.dto.LoginRequest;
import com.restaurant.restaurant.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1")
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

//    @RequestMapping("/")
//    public String greet() {
//        return "Hello World";
//    }
@GetMapping("customers/{email}")
public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
    return ResponseEntity.ok(customerService.retrieveCustomer(email));
}

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
//        system.out.println("controller");
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @DeleteMapping("customers/{id_}")
    public  ResponseEntity<String> deleteCustomer(@PathVariable("id_") String id) {
    return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.ok((customerService.login(request)));
    }
}
