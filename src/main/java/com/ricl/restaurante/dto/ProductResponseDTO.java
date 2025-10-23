package com.ricl.restaurante.dto;
import java.math.BigDecimal;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private String imageUrl;
    private String categoryName;

    public ProductResponseDTO() {}

    public ProductResponseDTO(Long id, String name, String description, BigDecimal price,
    Long categoryId, String imageUrl, String categoryName) {
        this.id = id;
        this.name = name;

        this.description = description;//?
        this.price = price;//?
        
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}



