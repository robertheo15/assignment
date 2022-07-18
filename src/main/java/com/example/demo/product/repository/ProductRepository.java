package com.example.demo.product.repository;

import com.example.demo.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Product update(Product oldEntity, Product newEntity);
}
