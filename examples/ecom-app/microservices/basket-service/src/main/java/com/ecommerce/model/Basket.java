package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Data
@Entity
@IdClass(Basket.PK.class)
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    @Id
    private Long userId;

    @Id
    private Long productId;

    @Id
    private Long sizeId;

    private Integer quantity;

    @EqualsAndHashCode
    public static class PK implements Serializable {

        private Long userId;

        private Long productId;

        private Long sizeId;

    }

}
