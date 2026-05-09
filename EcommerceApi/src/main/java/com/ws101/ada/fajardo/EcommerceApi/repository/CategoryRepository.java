package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Category entity.
 * Extends JpaRepository to provide CRUD operations and pagination.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * Custom finder method using Method Naming.
     * Finds a category by its name.
     * @param name the category name
     * @return Optional containing the Category if found
     */
    Optional<Category> findByName(String name);
}