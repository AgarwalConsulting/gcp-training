package com.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Category {

    @Id
    private Long categoryId;

    private String categoryName;

}
