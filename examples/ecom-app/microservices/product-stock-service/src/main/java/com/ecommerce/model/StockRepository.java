package com.ecommerce.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

    List<Stock> findAllByProductId(Long productId);

    Stock findByProductIdAndSizeId(Long productId, Long sizeId);

}
