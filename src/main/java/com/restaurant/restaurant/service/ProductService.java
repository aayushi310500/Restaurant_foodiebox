package com.restaurant.restaurant.service;



import com.restaurant.restaurant.dto.Product.ProductCreateRequest;
import com.restaurant.restaurant.dto.Product.ProductUpdateRequest;
import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.Product;
import com.restaurant.restaurant.mapper.ProductMapper;
import com.restaurant.restaurant.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public String createProduct(ProductCreateRequest request) {
        Product product = productMapper.toEntity(request);
//        customer.setPassword(encryptionService.encode(customer.getPassword()));
        productRepo.save(product);
        return "Product Added Successfully";
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }


    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        }
        return null;
    }

    public List<Product> getProductByRange(double low, double high) {
        return productRepo.findProductByRange(low,high);
    }


    public String deleteById(Long id) {
        Optional<Product> productById = productRepo.findById(id);
//        productRepo.deleteById(id);
        if(productById.isPresent()) {
            productRepo.deleteById(id);
            return "Product Deleted Successfully";
        }
        return "Product Deletion failed";
    }

    public String updateById(Long id, ProductUpdateRequest request) {
        Optional<Product> productById = productRepo.findById(id);
        if(productById.isPresent()) {
            Product product = productById.get();
            if(request.productName()!=null) {
                product.setProductName(request.productName());
            }
            if(request.productDescription()!=null) {
                product.setProductDescription(request.productDescription());
            }
            if(request.price()>0) {
                product.setPrice(request.price());
            }
            productRepo.save(product);
            return "Product Updated Successfully";
        }
        return "Product Update failed";
    }


}
