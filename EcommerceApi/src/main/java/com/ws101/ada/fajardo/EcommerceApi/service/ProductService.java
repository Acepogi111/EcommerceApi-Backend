package com.ws101.ada.fajardo.EcommerceApi.service;

import org.springframework.stereotype.Service;
import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private List<Product> productList = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();
    
    




    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product createProduct(Product product) {
        product.setId(idCounter.incrementAndGet());
        productList.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setPrice(updated.getPrice());
            existing.setStock(updated.getStock());
        }
        return existing;
    }

    public boolean deleteProduct(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }
}


