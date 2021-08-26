package com.ecommerce.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Size {

    @Id
    private Long sizeId;

    private String sizeCode;

}
