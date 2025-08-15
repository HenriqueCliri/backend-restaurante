package com.ricl.restaurante.controller;

import com.ricl.restaurante.dto.AddToCartRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ricl.restaurante.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ricl.restaurante.model.Order;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/add-item")
    public ResponseEntity<Order> addProductToOrder(@RequestBody AddToCartRequest request){
        Optional<Order> newOrder = orderService.addProductToOrder(request.getProductId(), request.getQuantity());

        if(newOrder.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
