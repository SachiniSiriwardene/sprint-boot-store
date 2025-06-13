package com.example.store.services;

import com.example.store.entities.Category;
import com.example.store.entities.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void addProduct() {
//        var product = Product.builder()
//                .name("Laptop")
//                .price(BigDecimal.valueOf(1000))
//                .description("High performance laptop")
//                .build();
//        var category     = Category.builder()
//                .name("Electronics")
//                .build();
//
//        product.setCategory(category);
//
//        productRepository.save(product);

        var category = categoryRepository.findById((byte) 1).orElseThrow();
        var product = Product.builder()
                .name("Product 1")
                .price(BigDecimal.valueOf(1000))
                .description("New product")
                .category(category)
                .build();
        productRepository.save(product);
    }

    public void deleteProduct() {
        productRepository.deleteById(1L);
    }

}
