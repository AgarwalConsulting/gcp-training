package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaRowDto implements Serializable {

    private Long productId;

    private Long sizeId;

    private Integer quantity;

}
