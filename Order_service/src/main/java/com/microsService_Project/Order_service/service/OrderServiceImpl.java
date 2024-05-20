package com.microsService_Project.Order_service.service;

import com.microsService_Project.Order_service.entity.Order;
import com.microsService_Project.Order_service.exception.CustomException;
import com.microsService_Project.Order_service.external.client.PaymentService;
import com.microsService_Project.Order_service.external.client.ProductService;
import com.microsService_Project.Order_service.external.client.request.PaymentRequest;
import com.microsService_Project.Order_service.external.client.response.PaymentResponse;
import com.microsService_Project.Order_service.external.client.response.ProductResponse;
import com.microsService_Project.Order_service.model.*;
import com.microsService_Project.Order_service.respository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;




    @Override
    public long add(OrderRequest orderRequest) {
        log.info("placing a order with id: ");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderStatus("Created")
                .OrderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .build();


        log.info("Calling payment service with order id: {}",order.getId());
        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(PaymentMode.valueOf(orderRequest.getPaymentMode().name()))
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus= null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successffuly");
            orderStatus="SUCCESS";
        } catch (Exception e){
            log.error("Error occured in payment");
            orderStatus="PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("order created succesfully with order id ", order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderById(long orderId) {
        log.info("getting orderDetails by orderId: {}",orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order is not found by id: "+orderId,"ORDER_ID INVALID"));

        log.info("Invoking Product Service to fetch the product for id :{}",order.getProductId());

        ProductResponse productResponse= restTemplate.getForObject("https://productService/product/"+order.getProductId(),ProductResponse.class);
        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();
        log.info("product details fetched successfully");
        log.info("fetching Payment details");
        PaymentResponse paymentResponse=null;
        try {
            paymentResponse=restTemplate.getForObject("http://paymentService/payment/order/"+orderId,PaymentResponse.class);
        } catch (Exception ex) {
            log.info("Payment not found with Order id: {}", orderId);
            throw new CustomException("Payment not found with Order id: " + orderId, "PAYMENT_NOT_FOUND");
        }

        OrderResponse.PaymentDetails paymentDetails= OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentDate(paymentResponse.getPaymentDate())
                .status(paymentResponse.getStatus())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();
        log.info("Payment details fetched");
        OrderResponse orderResponse= OrderResponse.builder()
                .amount(order.getAmount())
                .oderStatus(order.getOrderStatus())
                .OrderDate(order.getOrderDate())
                .productDetails(productDetails)
                .orderId(order.getId())
                .paymentDetails(paymentDetails)
                .build();

        log.info("order fetched succesfully by id: {}",orderId);
        return orderResponse;

    }
}
