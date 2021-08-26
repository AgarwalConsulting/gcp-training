package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDto {

    private Long productId;

    private Long sizeId;

    private String sizeCode;

    private Integer stock;

}
