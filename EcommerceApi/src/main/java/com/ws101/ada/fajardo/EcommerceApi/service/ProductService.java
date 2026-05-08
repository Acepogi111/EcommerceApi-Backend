package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import com.ws101.ada.fajardo.EcommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for product-related operations.
 * 
 * Provides business logic for creating, retrieving, updating, and deleting products.
 * This class acts as an intermediary between the API controller and the repository layer.
 * 
 * @author Acepogi111
 * @version 1.0
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products from the database.
     *
     * @return {@code List<Product>} containing all products. Returns empty list if no products exist.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id The ID of the product to retrieve. Must not be null.
     * @return {@code Optional<Product>} containing the product if found, empty Optional otherwise.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Saves a new product or updates an existing product in the database.
     *
     * @param product The product object to be saved. Must not be null.
     * @return The saved product with generated ID if new.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param id The ID of the product to delete. Must not be null.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Searches products by name and category.
     *
     * @param name The name keyword to search for. Can be null or empty.
     * @param category The category to filter by. Can be null or empty.
     * @return {@code List<Product>} containing matching products.
     */
    public List<Product> searchProducts(String name, String category) {
        return productRepository.findByNameContainingAndCategoryContaining(name, category);
    }
}