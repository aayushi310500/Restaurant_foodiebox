package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.dto.CustomerRequest;
import com.restaurant.restaurant.dto.Product.ProductCreateRequest;
import com.restaurant.restaurant.dto.Product.ProductUpdateRequest;
import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.Product;
import com.restaurant.restaurant.helper.EncryptionService;
import com.restaurant.restaurant.helper.JWTHelper;
import com.restaurant.restaurant.helper.JwtAuthValidate;
import com.restaurant.restaurant.mapper.CustomerMapper;
import com.restaurant.restaurant.mapper.ProductMapper;
import com.restaurant.restaurant.repo.CustomerRepo;
import com.restaurant.restaurant.repo.ProductRepo;
import com.restaurant.restaurant.service.CustomerService;
import com.restaurant.restaurant.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
//    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final CustomerService customerService;
//    private final EncryptionService encryptionService;
//    private final JWTHelper jwtHelper;
private final JwtAuthValidate jwtauthValidate;
    private final ProductService productService;
    private final ProductRepo productRepo;

    @PostMapping("/")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid ProductCreateRequest request){
        return  ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/range")
    public ResponseEntity<List<Product>> getProductByRange(@RequestParam double low,@RequestParam double high){
        return ResponseEntity.ok(productService.getProductByRange( low, high));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id,@RequestHeader("Authorization") String token){
        String email = jwtauthValidate.extractAndCheckToken(token);
        if(email==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok((productService.deleteById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable  Long id, @Valid @RequestBody ProductUpdateRequest request,@RequestHeader("Authorization") String token){
     String email = jwtauthValidate.extractAndCheckToken(token);
        if(email==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
//        Customer customer = customerService.getCustomer(email);
//        if(customer==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return ResponseEntity.ok((productService.updateById(id,request)));
    }

}
