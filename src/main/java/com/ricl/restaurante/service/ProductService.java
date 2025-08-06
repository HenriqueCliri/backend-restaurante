package com.ricl.restaurante.service;

import com.ricl.restaurante.model.Product;
import com.ricl.restaurante.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryService categoryService;

	public ProductService(ProductRepository productRepository, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> findProductsByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	public List<Product> searchProductsByName(String searchItem) {
		return productRepository.findByNameContainingIgnoreCase(searchItem);
	}
	
	public Product saveProduct(Product product) {
		if (product.getCategory() != null && product.getCategory().getId() != null) {
			categoryService.findCategoryById(product.getCategory().getId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Category with ID " + product.getId() + "does not exist"));
		} else {
			throw new IllegalArgumentException("Product must be associate with a valid category");
		}
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}