package com.restaurant.restaurant.dto.Product;

import com.fasterxml.jackson.annotation.JsonProperty;


    public record ProductResponse(
            @JsonProperty("product_name")
            String productName,
            @JsonProperty("product_description")
            String productDescription,
            @JsonProperty("price")
            double price
    ) {
    }

