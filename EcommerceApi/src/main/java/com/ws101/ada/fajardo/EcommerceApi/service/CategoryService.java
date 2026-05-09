package com.ws101.ada.fajardo.EcommerceApi.service;

import com.ws101.ada.fajardo.EcommerceApi.entity.Category;
import com.ws101.ada.fajardo.EcommerceApi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Category business logic.
 * Handles CRUD operations using CategoryRepository.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get all categories from database
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get category by ID
     */
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Create/Save new category to database
     */
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Delete category by ID
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}