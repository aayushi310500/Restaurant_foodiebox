package com.restaurant.restaurant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1")
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

//    @RequestMapping("/")
//    public String greet() {
//        return "Hello World";
//    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
//        system.out.println("controller");
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
