package com.ws101.ada.fajardo.EcommerceApi.dto;

import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    
    private String description;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
    
    private String categoryName;

    // FIX: Handle null category
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        
        // Check kung may category ba yung product
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
        } else {
            this.categoryId = null;
            this.categoryName = "No Category";
        }
    }
}