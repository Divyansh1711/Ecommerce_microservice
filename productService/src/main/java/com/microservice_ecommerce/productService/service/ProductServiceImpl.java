package com.microservice_ecommerce.productService.service;

import com.microservice_ecommerce.productService.entity.Product;
import com.microservice_ecommerce.productService.exception.ProductServiceCustomException;
import com.microservice_ecommerce.productService.model.ProductRequest;
import com.microservice_ecommerce.productService.model.ProductResponse;
import com.microservice_ecommerce.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
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
                .orElseThrow(()-> new ProductServiceCustomException("Product not found with given id: ", Long.toString(productId)));

        ProductResponse productResponse= new ProductResponse();
        copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
         log.info("reducing the quantity of product : " ,productId);

         Product product = productRepository.findById(productId)
                 .orElseThrow(()-> new ProductServiceCustomException("product not found by id :" ,"PRODUCT_NOT_FOUND"));

         if(product.getQuantity()<quantity){
             throw new ProductServiceCustomException("Product does not have sufficient quantity", "INSUFFICEINT_QUANTITY");
         }
         product.setQuantity(product.getQuantity()-quantity);
         productRepository.save(product);
         log.info("Product quantity reduced");
    }
}
