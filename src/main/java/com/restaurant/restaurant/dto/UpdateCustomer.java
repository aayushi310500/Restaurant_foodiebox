package com.restaurant.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCustomer (
    @JsonProperty("first_name")
    String firstName,

    @JsonProperty("last_name")
    String lastName
){}
