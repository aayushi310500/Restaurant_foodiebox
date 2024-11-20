package com.restaurant.restaurant.repo;

import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product p WHERE p.price BETWEEN :low AND :high ORDER BY p.price DESC LIMIT 2", nativeQuery = true)
    List<Product> findProductByRange(@Param("low") double low, @Param("high") double high);
//    Optional<Product> findByName(String productName);
//    deleteById(String id);
}