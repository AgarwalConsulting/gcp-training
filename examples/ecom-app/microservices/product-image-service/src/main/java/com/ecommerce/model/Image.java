package com.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long imageId;
    private String imageUrl;

}
