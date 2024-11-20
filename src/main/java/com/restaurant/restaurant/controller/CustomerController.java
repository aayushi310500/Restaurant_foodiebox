package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.dto.CustomerRequest;
import com.restaurant.restaurant.dto.CustomerResponse;
import com.restaurant.restaurant.dto.LoginRequest;
import com.restaurant.restaurant.dto.UpdateCustomer;
import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.helper.JwtAuthValidate;
import com.restaurant.restaurant.mapper.CustomerMapper;
import com.restaurant.restaurant.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1")
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final JwtAuthValidate jwtauthValidate;
//    private final UpdateCustomer updateCustomer;
//    @RequestMapping("/")
//    public String greet() {
//        return "Hello World";
//    }
@GetMapping("customers/{email}")
public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {

    return ResponseEntity.ok(customerService.retrieveCustomer(email));
}

//    @PostMapping("/customers")
//    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
////        system.out.println("controller");
//        return ResponseEntity.ok(customerService.createCustomer(request));
//    }

    @DeleteMapping("customers/{id_}")
    public  ResponseEntity<String> deleteCustomer(@PathVariable("id_") String id, @RequestHeader("Authorization") String token) {
        String email = jwtauthValidate.extractAndCheckToken(token);
        if(email==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Customer customer = customerService.getCustomer(email);
        if(customer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.ok((customerService.login(request)));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
    return  ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/customers/update")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid UpdateCustomer updateCustomerReq, @RequestHeader("Authorization") String token){
        String email = jwtauthValidate.extractAndCheckToken(token);
        if(email==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Customer customer = customerService.getCustomer(email);
        if(customer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    return ResponseEntity.ok((customerService.updateCustomer(email,updateCustomerReq)));
    }
}
