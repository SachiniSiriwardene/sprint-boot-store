package com.example.store.repositories;

import com.example.store.entities.Category;
import com.example.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {

    // Method to find products based on various criteria
    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice, Category category);
}
