package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Entity.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {

	List<Basket> findByUserId(Long userId);
	
	// to delete item from cart page
	void deleteByUserIdAndSizeIdAndProductId(Long userId, Long sizeId, Long productId);

	// as opposed to cart page, products are displayed in a grouped manner in navbar/checkout page
	// 0 as size_id is for the JPA's sake and not used throughout the project
	@Query(value = "SELECT b.user_id, b.product_id, SUM(b.quantity) as quantity, 0 as size_id "
			+ "FROM Basket b, Product p WHERE b.product_id = p.product_id AND user_id = :userId "
			+ "GROUP BY b.product_id", nativeQuery = true)
	List<Basket> findByUserIdGroupByProductId(@Param("userId") Long userId);
	
	@Query(value = "SELECT SUM(current_price * quantity) FROM Basket b, Product p "
			+ "WHERE b.product_id = p.product_id "
			+ "AND user_id = :userId", nativeQuery = true)
	Integer findBasketTotalByUserId(@Param("userId") Long userId);
	
	
}
