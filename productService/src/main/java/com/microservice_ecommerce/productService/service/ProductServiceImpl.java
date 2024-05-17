package com.microservice_ecommerce.productService.service;

import com.microservice_ecommerce.productService.entity.Product;
import com.microservice_ecommerce.productService.exception.ProductNotFoundCustomException;
import com.microservice_ecommerce.productService.model.ProductRequest;
import com.microservice_ecommerce.productService.model.ProductResponse;
import com.microservice_ecommerce.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long add(ProductRequest productRequest) {
       log.info("adding a product ...");

        Product product= Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity()).build();
        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundCustomException("Product not found with given id: ", productId));

        ProductResponse productResponse= new ProductResponse();
        copyProperties(product,productResponse);

        return productResponse;
    }
}
