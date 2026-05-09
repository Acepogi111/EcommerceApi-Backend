package com.ws101.ada.fajardo.EcommerceApi.controller;

import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import com.ws101.ada.fajardo.EcommerceApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
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
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    Product updatedProduct = productService.saveProduct(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // TASK 3 CUSTOM QUERIES - ITO YUNG BAGO
    @GetMapping("/category/{name}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String name) {
        List<Product> products = productService.getProductsByCategoryName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min, 
            @RequestParam Double max) {
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }
}
