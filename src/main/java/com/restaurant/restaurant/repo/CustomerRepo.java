package com.restaurant.restaurant.repo;

import com.restaurant.restaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
//    deleteById(String id);

}
