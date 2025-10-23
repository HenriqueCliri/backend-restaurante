package com.ricl.restaurante.controller;

import com.ricl.restaurante.dto.ProductResponseDTO;
import com.ricl.restaurante.model.Product;
import com.ricl.restaurante.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search) {

        List<ProductResponseDTO> products; ///como posso tratar dois tipos em um mesmo método?
        if(categoryId != null) {
            products = productService.findProductsByCategoryId(categoryId);
        } else if(search != null && !search.isEmpty()) {
            products = productService.searchProductsByName(search);
        }
        else {
            products = productService.findAllProductsDTO();
        }

        List<ProductResponseDTO> productsDTOs = products.stream()
        .map(ProductService::convertToDTO)
        .collect(Collectors.toList());

        return ResponseEntity.ok(productsDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.findProductDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        return productService.findProductById(id)
            .map(existingProduct -> {
                existingProduct.setName(productDetails.getName());
                existingProduct.setDescription(productDetails.getDescription());
                existingProduct.setPrice(productDetails.getPrice());
                existingProduct.setImageUrl(productDetails.getImageUrl());

                if(productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
                    existingProduct.setCategory(productDetails.getCategory());
                }

                Product updateProduct = productService.saveProduct(existingProduct);
                return ResponseEntity.ok(updateProduct);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if(productService.findProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}