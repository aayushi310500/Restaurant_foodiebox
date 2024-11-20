package com.restaurant.restaurant.dto.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductUpdateRequest (@JsonProperty("product_name")
                                   String productName,

                                   @JsonProperty("product_description")
                                   String productDescription,

                                   @JsonProperty("price")
                                   double price){}
