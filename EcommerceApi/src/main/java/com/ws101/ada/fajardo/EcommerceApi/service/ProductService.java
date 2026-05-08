package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import com.ws101.ada.fajardo.EcommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String filterType, String filterValue) {
        if (filterType == null || filterValue == null || filterValue.isEmpty()) {
            return new ArrayList<>();
        }
        
        if ("name".equalsIgnoreCase(filterType)) {
            return productRepository.findByNameContainingIgnoreCase(filterValue);
        } else if ("price".equalsIgnoreCase(filterType)) {
            try {
                Double price = Double.parseDouble(filterValue);
                return productRepository.findByPrice(price);
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}