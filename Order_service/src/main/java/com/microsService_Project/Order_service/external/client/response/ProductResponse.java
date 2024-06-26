package com.microsService_Project.Order_service.external.client.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String productName;
    private long productId;
    private long price;
    private long quantity;
}
