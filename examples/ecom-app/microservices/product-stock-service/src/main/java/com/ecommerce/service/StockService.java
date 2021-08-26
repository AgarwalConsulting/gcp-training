package com.ecommerce.service;

import com.ecommerce.model.StockDto;

import java.util.List;

public interface StockService {

    List<StockDto> getStockListByProductId(Long productId);

}
