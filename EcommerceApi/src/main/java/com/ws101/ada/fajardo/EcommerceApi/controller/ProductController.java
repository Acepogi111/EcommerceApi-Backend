package com.ws101.ada.fajardo.EcommerceApi.controller;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import com.ws101.ada.fajardo.EcommerceApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Successful GET -> 200 OK
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products); // 200 OK
    }

    // Product not found -> 404 Not Found | Successful GET -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Invalid request data -> 400 Bad Request | Successful GET -> 200 OK
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        
        if ("price".equalsIgnoreCase(filterType)) {
            try {
                double price = Double.parseDouble(filterValue);
                List<Product> products = productService.getProductsByPrice(price);
                return ResponseEntity.ok(products); // 200 OK
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().build(); // 400 Bad Request
            }
        }
        
        return ResponseEntity.badRequest().build(); // 400 Bad Request
    }

    // Successful POST creation -> 201 Created
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // 400 Bad Request kung walang name or price
        if (product.getName() == null || product.getName().isEmpty() || product.getPrice() <= 0) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED); // 201 Created
    }

    // Product not found -> 404 Not Found | Successful PUT -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = productService.getProductById(id);
        
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
        // 400 Bad Request validation
        if (productDetails.getName() == null || productDetails.getName().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        
        Product product = productOptional.get();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        
        Product updatedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(updatedProduct); // 200 OK
    }

    // Product not found -> 404 Not Found | Successful DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
        productService.deleteProduct(product.get());
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}