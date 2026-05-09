package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Order entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}