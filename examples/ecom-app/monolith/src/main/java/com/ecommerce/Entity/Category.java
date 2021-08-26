package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	// NOTE: This table is prepopulated in data.sql
	
	protected Category() {}
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Id
	private Long categoryId;
	private String categoryName;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	

}
