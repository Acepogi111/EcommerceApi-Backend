package com.ws101.ada.fajardo.EcommerceApi.controller;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import com.ws101.ada.fajardo.EcommerceApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for managing product operations.
 * 
 * Provides HTTP endpoints for CRUD operations on products including
 * create, read, update, delete, and search functionality.
 * 
 * @author Acepogi111
 * @version 1.0
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products from the database.
     *
     * @return {@code ResponseEntity<List<Product>>} containing all products with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve
     * @return {@code ResponseEntity<Product>} with product if found, or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new product.
     *
     * @param product The product object to create
     * @return {@code ResponseEntity<Product>} with created product and HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * Updates an existing product.
     *
     * @param id The ID of the product to update
     * @param product The updated product details
     * @return {@code ResponseEntity<Product>} with updated product or 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    Product updated = productService.saveProduct(product);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a product by ID.
     *
     * @param id The ID of the product to delete
     * @return {@code ResponseEntity<Void>} with HTTP 204 No Content or 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> {
                    productService.deleteProduct(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Searches products by name and category.
     *
     * @param name The name keyword to search for. Optional.
     * @param category The category keyword to search for. Optional.
     * @return {@code ResponseEntity<List<Product>>} with matching products
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String category) {
        List<Product> products = productService.searchProducts(name, category);
        return ResponseEntity.ok(products);
    }
}