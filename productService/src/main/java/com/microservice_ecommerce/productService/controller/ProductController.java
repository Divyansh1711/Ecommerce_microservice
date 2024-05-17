package com.microservice_ecommerce.productService.controller;

import com.microservice_ecommerce.productService.model.ProductRequest;
import com.microservice_ecommerce.productService.model.ProductResponse;
import com.microservice_ecommerce.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest product){
        long productId= productService.add(product);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long id){
        ProductResponse productResponse= productService.getProductById(id);
        return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }
}
