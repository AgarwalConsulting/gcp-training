package com.ecommerce.model;

import lombok.Data;

@Data
public class BasketRowDto {

    private Long productId;

    private Long sizeId;

    private Integer quantity;

    private Integer currentPrice;
}
