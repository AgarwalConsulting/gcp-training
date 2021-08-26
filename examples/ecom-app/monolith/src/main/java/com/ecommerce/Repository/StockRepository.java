package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Entity.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {
	
	List<Stock> findByProductId(Long productId);
	
	Stock findByProductIdAndSizeId(Long productId, Long sizeId);
	
	@Query(value = "SELECT SUM(current_stock) FROM Stock "
			+ "WHERE product_id = :productId", nativeQuery = true)
	Integer findTotalStockByProductId(@Param("productId") Long productId);
	

}
