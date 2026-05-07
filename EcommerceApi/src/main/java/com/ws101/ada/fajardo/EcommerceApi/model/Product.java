package com.ws101.ada.fajardo.EcommerceApi.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;      // Wala ka nito kanina
    private int stockQuantity;    // 'stock' lang name mo, dapat 'stockQuantity'
    private String imageUrl;      // Wala ka rin nito
}
