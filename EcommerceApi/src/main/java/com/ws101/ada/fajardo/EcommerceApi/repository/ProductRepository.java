package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Product entity.
 * 
 * Provides CRUD operations and custom query methods for products.
 * Extends JpaRepository to inherit standard database operations.
 * 
 * @author Acepogi111
 * @version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Finds products by name containing the given keyword.
     * Case-insensitive search.
     *
     * @param name The name keyword to search for
     * @return {@code List<Product>} containing matching products
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * Finds products by category containing the given keyword.
     * Case-insensitive search.
     *
     * @param category The category keyword to search for
     * @return {@code List<Product>} containing matching products
     */
    List<Product> findByCategoryContainingIgnoreCase(String category);
    
    /**
     * Finds products by name and category containing the given keywords.
     * Case-insensitive search. Both parameters can be empty strings.
     *
     * @param name The name keyword to search for
     * @param category The category keyword to search for  
     * @return {@code List<Product>} containing matching products
     */
    List<Product> findByNameContainingAndCategoryContaining(String name, String category);
}