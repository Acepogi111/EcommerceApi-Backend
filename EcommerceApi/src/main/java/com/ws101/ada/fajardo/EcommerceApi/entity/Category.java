package com.ws101.ada.fajardo.EcommerceApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // <-- DAPAT MERON NITO
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore // <-- ITO YUNG PINAKA IMPORTANTE
    private List<Product> products = new ArrayList<>();

    // Constructors
    public Category() {}

    // Getters and Setters - DAPAT KOMPLETO
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}