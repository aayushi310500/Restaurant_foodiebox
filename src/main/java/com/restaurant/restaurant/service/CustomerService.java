package com.restaurant.restaurant.service;

import com.restaurant.restaurant.dto.CustomerRequest;
import com.restaurant.restaurant.dto.CustomerResponse;
import com.restaurant.restaurant.dto.LoginRequest;
import com.restaurant.restaurant.dto.UpdateCustomer;
import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.exception.CustomerNotFoundException;
import com.restaurant.restaurant.helper.EncryptionService;
import com.restaurant.restaurant.helper.JWTHelper;
import com.restaurant.restaurant.helper.JwtAuthValidate;
import com.restaurant.restaurant.mapper.CustomerMapper;
import com.restaurant.restaurant.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;
//import jwtHelper

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public  String updateCustomer(String email, UpdateCustomer request) {

        Optional<Customer> customer = customerRepo.findByEmail(email);
        if (customer.isPresent()) {
            Customer customerEntity = customer.get();
            if (request.firstName() != null) {
                customerEntity.setFirstName(customerEntity.getFirstName());
            }
            if (request.lastName() != null) {
                customerEntity.setLastName(customerEntity.getLastName());
            }
//            customerEntity.setUpdatedOn(LocalDateTime.now());
            customerRepo.save(customerEntity);
        }

        return "Customer updated Successfully";

    }

//    public static Object updateCustomer(@Valid CustomerRequest request) {
//    }


    ;
//    private final EncryptionService encryptionService;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {

        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String deleteCustomerById(String id) {
            long id_ = Long.parseLong(id);

        customerRepo.deleteById(id_);
        return "Customer Deleted Successfully";
    }
    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
//      return  "Login Done!";
        return jwtHelper.generateToken(request.email());
    }

}
