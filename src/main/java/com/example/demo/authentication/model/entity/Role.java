package com.example.demo.authentication.model.entity;

import common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity  {

    @Column(length = 60)
    private String name;
}

