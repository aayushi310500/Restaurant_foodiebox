package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.dto.Product.ProductCreateRequest;
import com.restaurant.restaurant.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductCreateRequest request) {
        return Product.builder()
                .productName(request.productName())
                .productDescription(request.productDescription())
                .price(request.price())
                .build();
    }
//
//    public Product toEntity(ProductRequest.ProductUpdateRequest request) {
//        return Product.builder()
//                .name(request.name())
//                .price(request.price())
//                .build();
//    }
}
