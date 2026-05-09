package com.ws101.ada.fajardo.EcommerceApi.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
/**
 * Represents a customer order in the e-commerce system.
 * 
 * Relationship: One Order has Many OrderItems (One-to-Many).
 * Uses CascadeType.ALL so saving an order also saves all order items.
 * Uses FetchType.LAZY to load order items only when accessed.
 */
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    
    private LocalDateTime orderDate = LocalDateTime.now();
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {}

    public Order(Long id, String customerName, LocalDateTime orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}