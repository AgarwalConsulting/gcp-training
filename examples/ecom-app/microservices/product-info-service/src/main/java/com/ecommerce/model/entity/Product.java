package com.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private Long categoryId;

    private Integer currentPrice;


    /*
    @Column(length=1000)
    private String description;
    */
}
