package com.ws101.ada.fajardo.EcommerceApi.controller;

import com.ws101.ada.fajardo.EcommerceApi.exception.ProductNotFoundException;
import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import com.ws101.ada.fajardo.EcommerceApi.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @PostMapping
public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    Product savedProduct = productService.saveProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
}
    

    @PutMapping("/{id}")
public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
    Product product = productService.getProductById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    
    product.setName(productDetails.getName());
    product.setPrice(productDetails.getPrice());
    product.setDescription(productDetails.getDescription());
    
    Product updatedProduct = productService.saveProduct(product);
    return ResponseEntity.ok(updatedProduct);
}

    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        
        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }
        if (updates.containsKey("price")) {
            product.setPrice(Double.parseDouble(updates.get("price").toString()));
        }
        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }
        
        Product updatedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productService.deleteProduct(id); // ID yung pinapasa, hindi Product
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        List<Product> products = productService.searchProducts(filterType, filterValue);
        return ResponseEntity.ok(products);
    }
}