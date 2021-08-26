package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesDetail {

	protected SalesDetail() { }
	
	public SalesDetail(Long saleId, Long productId, Integer quantity, Long sizeId, Integer itemPrice) {
		this.saleId = saleId;
		this.productId = productId;
		this.quantity = quantity;
		this.sizeId = sizeId;
		this.itemPrice = itemPrice;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long detailId;
	
	private Long saleId;
	private Long productId;
	private Integer quantity;
	private Long sizeId;
	private Integer itemPrice;
	
	
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	

	

}
