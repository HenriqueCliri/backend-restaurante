package com.ricl.restaurante.service;
import com.ricl.restaurante.repository.*;
import com.ricl.restaurante.controller.CategoryController;
import com.ricl.restaurante.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {

    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository, CategoryRepository categoryRepository, CategoryController categoryController) {
                    this.orderRepository = orderRepository;
                    this.productRepository = productRepository;
                    this.orderItemRepository = orderItemRepository;
                    this.categoryRepository = categoryRepository;
                }

    @Transactional
    public Optional<Order> addProductToOrder(Long productId, int quantity) {

        Order activeOrder = findOrCreateActiveOrder();
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }
        Product product = optionalProduct.get();

        Optional<OrderItem> existingItem = activeOrder
        .getOrderItems()
        .stream()
        .filter(item -> item.getProduct()
        .getId()
        .equals(productId))
        .findFirst();
        
        if(existingItem.isPresent()){
            OrderItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            orderItemRepository.save(item);
        } else {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(activeOrder);
            activeOrder.getOrderItems().add(orderItem);
        }

        Order savedOrder = orderRepository.save(activeOrder);
        return Optional.of(savedOrder);
    }

    public Optional<Order> findActiveOrder() {
        return orderRepository.findTopByOrderByIdDesc();
    }

    private Order findOrCreateActiveOrder() {
        return orderRepository.findTopByOrderByIdDesc().orElseGet(() -> {
            Order newOrder = new Order();
            newOrder.setCustomerName("Ghest");
            return orderRepository.save(newOrder);
        });
    }

    @Transactional
    public Optional<OrderItem> updateOrderItemQuantity(Long orderItemId, int quantity) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if(optionalOrderItem.isPresent()) {
            OrderItem item = optionalOrderItem.get();
            item.setQuantity(quantity);
            return Optional.of(orderItemRepository.save(item));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean removeOrderItem(Long orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if(optionalOrderItem.isPresent()) {
            orderItemRepository.deleteById(orderItemId);
            return true;
        }
        return false;
    }

    @Transactional
    public void clearAllData() {
        orderRepository.deleteAll();
        orderItemRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }
}
