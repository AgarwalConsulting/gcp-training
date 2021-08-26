package com.ecommerce.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product {
	
	public Product() {}
	
	public Product(String productName, Integer currentPrice, String size, String descr, Long categoryId,
			List<String> imageUrls, List<Stock> stockInfo, Integer quantity) {
		this.productName = productName;
		this.currentPrice = currentPrice;
		this.size = size;
		this.descr = descr;
		this.categoryId = categoryId;
		this.imageUrls = imageUrls;
		this.stockInfo = stockInfo;
		this.quantity = quantity;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private Integer currentPrice;
	private String size; // fetched from instagram in the pattern "M/L/XL" etc.
	 
	@Column(length=1000)
	private String descr;
	private Long categoryId;
	
	// These fields are not kept in the database but persisted in ProductService
	// If performance is a bigger concern than data storage, this can be changed
	@Transient
	private List<String> imageUrls;
	@Transient
	private List<Stock> stockInfo;
	@Transient
	private Integer quantity; // used for basket related operations
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public List<Stock> getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(List<Stock> stockInfo) {
		this.stockInfo = stockInfo;
	}	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	
	
	
}
