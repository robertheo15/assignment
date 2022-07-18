package com.example.demo.cart.model.entity;

import com.example.demo.authentication.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    @NotNull
    private User user;

    @JsonIgnoreProperties(value = "id", allowSetters = true)
    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    @Valid
    private List<CartDetail> cartDetails;
}
