package com.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto implements Serializable {

    private Long productId;

    private Long sizeId;

    private String sizeCode;

    private Integer stock;

}
