package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductId(Long productId);
	
	List<Product> findByProductName(String productName);
	
	List<Product> findFirst4ByCategoryIdOrderByProductIdDesc(Long categoryId);
	
	
	@Query(value = "SELECT * FROM Product "
		 + "WHERE category_id = :categoryId AND product_id <> :productId "
		 + "ORDER BY ABS( current_price - :productPrice ) "
		 + "LIMIT 4", nativeQuery = true)
	List<Product> findRelatedProducts(@Param("categoryId") Long categoryId, 
									  @Param("productId") Long productId, 
									  @Param("productPrice") Integer productPrice);
	
}
