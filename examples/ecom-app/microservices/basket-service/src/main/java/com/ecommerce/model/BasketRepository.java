package com.ecommerce.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends CrudRepository<Basket, Long> {

    List<Basket> findAllByUserId(Long userId);

    @Query(value = "SELECT product_id as productId, SUM(quantity) AS quantity FROM Basket "
            + "WHERE user_id = :userId GROUP BY product_id", nativeQuery = true)
    List<BasketRepoDto> findBasketSummaryByUserId(@Param("userId") Long userId);

    void deleteByProductIdAndSizeIdAndUserId(Long productId, Long sizeId, Long userId);

}
