package com.ws101.ada.fajardo.EcommerceApi.controller;

import com.ws101.ada.fajardo.EcommerceApi.entity.Category;
import com.ws101.ada.fajardo.EcommerceApi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Category endpoints.
 * Controller → Service → Repository pattern
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // GAMITIN SI SERVICE, HINDI REPO

    // GET ALL - Kuha lahat ng categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET BY ID - Kuha isang category
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Create new category - ITO YUNG WALA KA KANINA
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // DELETE - Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}