package com.restaurant.restaurant.dto.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductCreateRequest(
        @NotNull(message = "Product name should be present")
        @NotEmpty(message = "Product name should be present")
        @NotBlank(message = "Product name should be present")
        @JsonProperty("product_name")
        String productName,

        @JsonProperty("product_description")
        String productDescription,

        @Digits(integer = 6, fraction = 2)
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be a positive value")
        @JsonProperty("price")
        double price
){
}