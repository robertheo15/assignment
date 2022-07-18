package com.example.demo.cart.model.entity;

import com.example.demo.product.model.entity.Product;
import common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Setter
@Getter
@Entity
@Table(name = "cart_details", uniqueConstraints = @UniqueConstraint(columnNames = {"product_id"}))
public class CartDetail extends BaseEntity {

    @JoinColumn(name = "cart_id", nullable = false)
    @OneToOne
    private Cart cart;

    @JoinColumn(name = "product_id", nullable = false)
    @OneToOne
    private Product product;
}
