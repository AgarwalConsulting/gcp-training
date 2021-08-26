package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaRowDto implements Serializable {

    private Long productId;

    private Long sizeId;

    private Integer quantity;

}
