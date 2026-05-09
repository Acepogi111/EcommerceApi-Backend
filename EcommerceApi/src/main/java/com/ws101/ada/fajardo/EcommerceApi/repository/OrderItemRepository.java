package com.ws101.ada.fajardo.EcommerceApi.repository;

import com.ws101.ada.fajardo.EcommerceApi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for OrderItem entity.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}