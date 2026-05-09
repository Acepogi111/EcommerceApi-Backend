package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    

    @Query("SELECT p FROM Product p JOIN FETCH p.category c WHERE c.name = :name")
    List<Product> findByCategoryName(@Param("name") String name);
        @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);
    
}