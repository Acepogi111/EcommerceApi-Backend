package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import com.ws101.ada.fajardo.EcommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    // TASK 3 CUSTOM QUERIES - ITO YUNG BAGO
    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}