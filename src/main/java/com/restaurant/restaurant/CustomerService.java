package com.restaurant.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
//    private final EncryptionService encryptionService;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
//        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

}
