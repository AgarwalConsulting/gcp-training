package com.ecommerce.model.repository;

import com.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByProductId(Long productId);

    // TODO LocalDate product attribute
    List<Product> findFirst4ByCategoryIdOrderByProductIdDesc(Long categoryId);

    @Query(value = "SELECT * FROM Product "
            + "WHERE category_id = :categoryId AND product_id <> :productId "
            + "ORDER BY ABS( current_price - :currentPrice ) "
            + "LIMIT 4", nativeQuery = true)
    List<Product> findRelatedProducts(@Param("productId") Long productId,
                                      @Param("categoryId") Long categoryId,
                                      @Param("currentPrice") Integer currentPrice);

}