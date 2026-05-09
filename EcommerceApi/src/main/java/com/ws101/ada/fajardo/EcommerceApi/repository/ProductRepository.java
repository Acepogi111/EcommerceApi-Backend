package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // List<Product> findByCategoryName(String categoryName); // ← Comment mo to
}