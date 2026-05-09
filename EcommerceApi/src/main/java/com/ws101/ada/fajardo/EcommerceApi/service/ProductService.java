package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.dto.ProductDTO;
import com.ws101.ada.fajardo.EcommerceApi.entity.Category;
import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import com.ws101.ada.fajardo.EcommerceApi.exception.ResourceNotFoundException;
import com.ws101.ada.fajardo.EcommerceApi.repository.CategoryRepository;
import com.ws101.ada.fajardo.EcommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
    
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
        product.setCategory(category);
        
        return product;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return convertToDTO(product);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}