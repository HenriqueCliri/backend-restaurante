package com.ricl.restaurante.service;

import com.ricl.restaurante.model.Product;
import com.ricl.restaurante.dto.ProductResponseDTO;
import com.ricl.restaurante.model.Category;

import com.ricl.restaurante.repository.ProductRepository;
import com.ricl.restaurante.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public static ProductResponseDTO convertToDTO(Product product) {
		Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
		String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;

		return new ProductResponseDTO(
			product.getId(),
			product.getName(),
			product.getDescription(),
			product.getPrice(),
			categoryId,
			product.getImageUrl(),
			categoryName
		);
	}

	public List<ProductResponseDTO> findAllProductsDTO() {
		return productRepository.findAll().stream()
		.map(ProductService::convertToDTO)
		.collect(Collectors.toList());
	}

	public Optional<ProductResponseDTO> findProductDTOById(Long id) {
		return productRepository.findById(id)
		.map(ProductService::convertToDTO);
	}
	
	//devo usar o DTO para o restante dos métodos?
	public List<Product> findProductsByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	public List<Product> searchProductsByName(String searchItem) {
		return productRepository.findByNameContainingIgnoreCase(searchItem);
	}

	public Product saveProduct(Product product) {
		if (product.getCategory() != null && product.getCategory().getId() != null) {
			categoryRepository.findById(product
			.getCategory()
			.getId())
			.orElseThrow(() -> new IllegalArgumentException("Category with ID " + product.getCategory().getId() + "does not exist"));
		} else {
			throw new IllegalArgumentException("Product must be associate with a valid category");
		}
		return productRepository.save(product);
	}

	@Transactional
	public void initializeData() {
		Category mainDishes = new Category("Pratos Principais");
		Category drinks = new Category("Bebidas");
		categoryRepository.saveAll(List.of(mainDishes, drinks));

		Product burger = new Product(
				"Hambúrguer Clássico",
				"Hambúrguer suculento com queijo, alface, tomate e molho especial.",
				new BigDecimal("29.90"),
				"https://images.unsplash.com/photo-1571091718767-18b5b1457add",
				mainDishes);
		productRepository.save(burger);

		Product soda = new Product(
				"Refrigerante Lata",
				"Lata de 350ml de refrigerante a sua escolha.",
				new BigDecimal("6.50"),
				"https://images.unsplash.com/photo-1599818826702-861f1c243886",
				drinks);
		productRepository.save(soda);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}