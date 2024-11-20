package com.restaurant.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name="product_name",nullable = false)
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name = "price",nullable = false)
    private double price;

}
