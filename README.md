# EcommerceApi-Backend

REST API for E-commerce product management built with Spring Boot.

## Features

- CRUD operations for products
- Search products by name and category
- Full Javadoc documentation

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL / H2 Database
- Gradle

## Setup Instructions

### Prerequisites

- JDK 17 or higher
- MySQL 8.0+ or use H2 in-memory DB
- Gradle 8.x

### Run Locally

1. Clone the repository

   ```bash
   git clone https://github.com/Acepogi111/EcommerceApi-Backend.git

---

## Issue 1: Database Integration - Task 9 Documentation

## Database Schema

### Table: products

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique product identifier |
| name | VARCHAR(255) | NOT NULL | Product name |
| price | DECIMAL(10,2) | NOT NULL | Product price in PHP |
| description | TEXT | NULL | Product description/details |
| stock | INT | NOT NULL | Available quantity |

*Relationships:* Single table only. No foreign keys implemented yet for Issue 1.

## API Endpoints

*Base URL:* `http://localhost:8080/api/products`

| Method | Endpoint | Description | Request Body | Status |
| --- | --- | --- | --- | --- |
| GET | /api/products | Get all products from database | None | 200 OK |
| GET | /api/products/{id} | Get product by ID | None | 200 OK / 404 |
| POST | /api/products | Create new product | JSON Product | 201 Created |
| PUT | /api/products/{id} | Update existing product | JSON Product | 200 OK |
| DELETE | /api/products/{id} | Delete product | None | 204 No Content |

*CORS Configuration:* Allowed origins: `http://localhost:5500`, `http://localhost:8080`

### Sample POST Request Body

```json


  "name": "iPhone 15",
  "price": 55000.00,
  "description": "Latest Apple smartphone with A17 chip",
  "stock": 50
