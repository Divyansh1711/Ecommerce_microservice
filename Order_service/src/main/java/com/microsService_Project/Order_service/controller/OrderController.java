package com.microsService_Project.Order_service.controller;

import com.microsService_Project.Order_service.model.OrderRequest;
import com.microsService_Project.Order_service.model.OrderResponse;
import com.microsService_Project.Order_service.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId= orderService.add(orderRequest);
        log.info("order Id: ",orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('Customer') || hasAuthority('Admin') ")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){

        OrderResponse orderResponse =orderService.getOrderById(orderId);

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('Customer') || hasAuthority('Admin') ")
    @GetMapping("/hello")
    public String getOrderDetails(){
        log.info("method is called");
        return "hello";
    }

}
