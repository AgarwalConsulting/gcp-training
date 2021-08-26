package com.ecommerce.Dto;

import java.util.List;

import com.ecommerce.Entity.Product;

public class BasketDto {

	
	public BasketDto(List<Product> items, List<Integer> basketQtys, List<Integer> maxQtys,
			List<String> sizeCodes, List<Long> sizeIds) {
		this.items = items;
		this.basketQtys = basketQtys;
		this.maxQtys = maxQtys;
		this.sizeCodes = sizeCodes;
		this.sizeIds = sizeIds;
	}
	
	private List<Product> items;
	private List<Integer> basketQtys;
	private List<Integer> maxQtys;
	private List<String> sizeCodes;
	private List<Long> sizeIds;
	
	
	public List<Product> getItems() {
		return items;
	}
	public void setItems(List<Product> items) {
		this.items = items;
	}
	public List<Integer> getBasketQtys() {
		return basketQtys;
	}
	public void setBasketQtys(List<Integer> basketQtys) {
		this.basketQtys = basketQtys;
	}
	public List<Integer> getMaxQtys() {
		return maxQtys;
	}
	public void setMaxQtys(List<Integer> maxQtys) {
		this.maxQtys = maxQtys;
	}
	public List<String> getSizeCodes() {
		return sizeCodes;
	}
	public void setSizeCodes(List<String> sizeCodes) {
		this.sizeCodes = sizeCodes;
	}
	public List<Long> getSizeIds() {
		return sizeIds;
	}
	public void setSizeIds(List<Long> sizeIds) {
		this.sizeIds = sizeIds;
	}
	
	
	
	
	
}
