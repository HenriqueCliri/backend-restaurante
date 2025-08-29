package com.ricl.restaurante.service;

import com.ricl.restaurante.model.Product;
import com.ricl.restaurante.model.Category;

import com.ricl.restaurante.repository.ProductRepository;
import com.ricl.restaurante.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
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
			categoryRepository.findCategoryById(product.getCategory().getId())
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

	@Transactional
    public void initializeData() {
        Category mainDishes = new Category("Pratos Principais");
        Category drinks = new Category("Bebidas");
        categoryRepository.saveAll(List.of(mainDishes, drinks));
        
        Product burger = new Product(
            "Hamburguer", "Descrição", new BigDecimal("29.90"),
            "url", mainDishes
        );
        productRepository.save(burger);
    }
}