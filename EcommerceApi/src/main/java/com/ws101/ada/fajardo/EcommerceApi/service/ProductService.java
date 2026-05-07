package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    private List<Product> productList = new ArrayList<>();
    private Long nextId = 1L; // 3.2: Simple counter para sa unique ID

    public ProductService() {
        // 3.1.2: Initialize with at least 10 sample products
        productList.add(new Product(nextId++, "Gaming Laptop", "16GB RAM RTX 4060", 65000.00, "Electronics", 5, "https://example.com/laptop.jpg"));
        productList.add(new Product(nextId++, "Wireless Mouse", "Ergonomic Gaming Mouse", 1200.00, "Accessories", 30, "https://example.com/mouse.jpg"));
        productList.add(new Product(nextId++, "Mechanical Keyboard", "RGB Mechanical Keyboard", 3500.00, "Accessories", 15, "https://example.com/keyboard.jpg"));
        productList.add(new Product(nextId++, "4K Monitor", "27 inch 4K IPS Display", 18000.00, "Electronics", 8, null));
        productList.add(new Product(nextId++, "USB-C Hub", "7-in-1 USB-C Hub", 1500.00, "Accessories", 25, null));
        productList.add(new Product(nextId++, "Webcam HD", "1080p Webcam with Mic", 2200.00, "Electronics", 12, "https://example.com/webcam.jpg"));
        productList.add(new Product(nextId++, "Office Chair", "Ergonomic Office Chair", 8500.00, "Furniture", 6, null));
        productList.add(new Product(nextId++, "Desk Lamp", "LED Desk Lamp", 800.00, "Furniture", 20, null));
        productList.add(new Product(nextId++, "SSD 1TB", "NVMe SSD 1TB", 4200.00, "Storage", 18, "https://example.com/ssd.jpg"));
        productList.add(new Product(nextId++, "Power Bank", "20000mAh Fast Charging", 1800.00, "Accessories", 40, null));
    }

    // 3.1.3: Retrieving all products
    public List<Product> getAllProducts() {
        return productList;
    }

    // 3.1.3: Finding a product by ID
    public Optional<Product> getProductById(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    // 3.1.3: Creating a new product
    public Product createProduct(Product product) {
        product.setId(nextId++); // 3.2: Auto-increment ID
        productList.add(product);
        return product;
    }

    // 3.1.3: Updating an existing product
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImageUrl(updatedProduct.getImageUrl());
            return product;
        }
        return null; // Or throw exception
    }

    // 3.1.3: Deleting a product
    public boolean deleteProduct(Long id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }

    // 3.1.3: Filtering by category
    public List<Product> getProductsByCategory(String category) {
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // 3.1.3: Filtering by name
    public List<Product> searchProductsByName(String name) {
        return productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 3.1.3: Filtering by price range
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
