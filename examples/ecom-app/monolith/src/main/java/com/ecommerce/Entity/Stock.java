package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Stock {
	
	// This entity's designated composite keys are productId/sizeId, yet implemented with stockId
	// The reason is, as opposed to basket table which also has composite key, therefore an IdClass of its own	
	// This table is ONLY inserted by the python script which makes it unreasonable
	// To further implement an StockId.Class + make it more complex than necessary 
	
	protected Stock() {}
	
	public Stock(Long productId, Long sizeId, Integer currentStock, Integer lastFetchedStock) {
		this.productId = productId;
		this.sizeId = sizeId;
		this.currentStock = currentStock;
		this.lastFetchedStock = lastFetchedStock;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stockId;
	private Long productId;
	private Long sizeId;
	private Integer currentStock;
	private Integer lastFetchedStock;
	

	// NOTE THAT this field is populated in the ProductService
	// Using a custom setter with a (Long sizeId) parameter
	@Transient
	private String sizeCode; // e.g. S, M, L, XL, XXL
	

	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
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
	public Integer getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}
	public Integer getLastFetchedStock() {
		return lastFetchedStock;
	}
	public void setLastFetchedStock(Integer lastFetchedStock) {
		this.lastFetchedStock = lastFetchedStock;
	}
	public String getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}
	public void setSizeCode(Long sizeId) {
		switch(Math.toIntExact(sizeId)) {
		case 1:
			this.sizeCode = "S";
		 	break;
		case 2:
			this.sizeCode = "M";
			break;
		case 3:
			this.sizeCode = "L";
		 	break;
		case 4:
			this.sizeCode = "XL";
			break;
		case 5:
			this.sizeCode = "XXL";
			break;
		}
	}
	
	
	
	
	
}
