package com.example.store.services;

import com.example.store.dtos.ProductSummary;
import com.example.store.dtos.ProductSummaryDTO;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.repositories.ProductCriteriaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    List<Product> findByName(String name);

    @Query(value = "SELECT * FROM products p WHERE p.price BETWEEN :min AND :max order by p.name", nativeQuery = true)
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    //jpql
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max order by p.name")
    List<Product> findProductsJP(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("select p from Product p where p.price between :min and :max order by p.name")
    List<Product> findByPriceBetweenOrderByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Procedure("findProductsByPrice")
    List<Product> findProduct(BigDecimal min, BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    List<ProductSummaryDTO> findByCategory(Category category);

}