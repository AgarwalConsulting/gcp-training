package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(BasketID.class)
public class Basket {
	
	protected Basket() { }

	public Basket(Long userId, Long productId, Long sizeId, Integer quantity) {
		this.userId = userId;
		this.productId = productId;
		this.sizeId = sizeId;
		this.quantity = quantity;
	}


	// Note that each user may have ONLY one record
	// for each combination of product/size (IDs) 
	@Id
	private Long userId;	
	@Id
	private Long productId;
	@Id
	private Long sizeId;
	
	private Integer quantity;

	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
