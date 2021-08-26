package com.ecommerce.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SalesDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long detailId;

    private Long saleId;

    private Long productId;

    private Integer quantity;

    private Long sizeId;

    private Integer itemPrice;

}
