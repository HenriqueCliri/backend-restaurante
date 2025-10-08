package com.ricl.restaurante.controller;

import com.ricl.restaurante.dto.UpdateQuantityRequest;
import com.ricl.restaurante.dto.AddToCartRequest;

import org.springframework.http.ResponseEntity;
import com.ricl.restaurante.service.ProductService;
import org.springframework.http.HttpStatus;

import com.ricl.restaurante.service.OrderService;

import com.ricl.restaurante.model.OrderItem;
import com.ricl.restaurante.model.Order;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(OrderService orderService, ProductController productController, ProductService productService){
        this.orderService = orderService;
        this.productService = productService;
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

    @GetMapping("/cart")
    public ResponseEntity<Order> getActiveOrder() {
        Optional<Order> activeOrder = orderService.findActiveOrder();
        return activeOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("items/{id}")
    public ResponseEntity<OrderItem> updateOrderItemQuantity(@PathVariable Long id, @RequestBody UpdateQuantityRequest request) {
        Optional<OrderItem> updatedItem = orderService.updateOrderItemQuantity(id, request.getQuantity());
        return updatedItem.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeOrderItem(@PathVariable Long id) {
        if(orderService.removeOrderItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reset-data")
    public ResponseEntity<Void> resetDatabase() {
        orderService.clearAllData();
        productService.initializeData();
        return ResponseEntity.noContent().build();
    }
}
