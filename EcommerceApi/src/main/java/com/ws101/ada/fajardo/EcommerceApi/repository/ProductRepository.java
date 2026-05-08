package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Wala munang custom methods para hindi mag-crash
    // Lahat ng basic CRUD meron na sa JpaRepository: 
    // findAll(), findById(), save(), deleteById(), etc.
    
}