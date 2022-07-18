package com.example.demo.cart.repository;

import com.example.demo.cart.model.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
//    CartDetail update(CartDetail oldEntity, CartDetail newEntity);
}
