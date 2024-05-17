package com.microservice_ecommerce.productService.service;

import com.microservice_ecommerce.productService.model.ProductRequest;
import com.microservice_ecommerce.productService.model.ProductResponse;

public interface ProductService {
    long add(ProductRequest product);

    ProductResponse getProductById(long productId);
}
