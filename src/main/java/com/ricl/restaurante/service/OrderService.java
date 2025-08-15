package com.ricl.restaurante.service;

import org.springframework.stereotype.Service;

import com.ricl.restaurante.repository.OrderItemRepository;
import com.ricl.restaurante.repository.OrderRepository;
import com.ricl.restaurante.repository.ProductRepository;
import com.ricl.restaurante.model.Order;
import com.ricl.restaurante.model.OrderItem;
import com.ricl.restaurante.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
                    this.orderRepository = orderRepository;
                    this.productRepository = productRepository;
                    this.orderItemRepository = orderItemRepository;
                }

    @Transactional
    public Optional<Order> addProductToOrder(Long productId, int quantity) {
        
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Order activeOrder = findOrCreateActiveOrder();

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

        Order order = new Order();
        order.setCustomerName("Guest");

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice());
        orderItem.setOrder(order);

        order.getOrderItems().add(orderItem);

        Order saveOrder = orderRepository.save(order);
        return Optional.of(saveOrder);
    }

}