package com.ecommerce.Dto;

import java.util.ArrayList;

public class FilterDto {

	public String rawSearchString;
	public Integer minPrice;
	public Integer maxPrice;
	public Integer sortBy;
	public ArrayList<Integer> categoryList;
	public ArrayList<Integer> sizeList;
	
	public FilterDto(String rawSearchString, Integer minPrice, Integer maxPrice, 
			Integer sortBy, ArrayList<Integer> categoryList, ArrayList<Integer> sizeList) {
		
		this.rawSearchString = rawSearchString;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.sortBy = sortBy;
		this.categoryList = categoryList;
		this.sizeList = sizeList;
		
	}

	public String getRawSearchString() {
		return rawSearchString;
	}
	
	public void setRawSearchString(String rawSearchString) {
		this.rawSearchString = rawSearchString;
	}
	
	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public ArrayList<Integer> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Integer> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<Integer> getSizeList() {
		return sizeList;
	}

	public void setSizeList(ArrayList<Integer> sizeList) {
		this.sizeList = sizeList;
	}
	

	public String getQueryOf() {
		
		String nameCondition = "(p.product_name LIKE '%" + rawSearchString + "%')";
		
		String priceCondition = "(current_price BETWEEN " + minPrice + " AND " + maxPrice + ")";
		
		String categoryCondition;
		if(categoryList.size() == 1) {
			categoryCondition = "(p.category_id = " + categoryList.get(0) + ")";
		}
		else {
			categoryCondition = "(p.category_id = " + categoryList.get(0);
			for(Integer cat : categoryList) {
				String appendix = " OR p.category_id = " + cat;
				categoryCondition += appendix;
			}
			categoryCondition += ")";
		}
		
		// Handle sizeCondition
		ArrayList<String> sizeConditions = new ArrayList<String>();
		for(Integer size : sizeList) {
			String condition = "(s.size_id = " + size + " AND current_stock > 0)";
			sizeConditions.add(condition);
		}
		
		String sizeCondition = "(";
		for(String condition : sizeConditions) {
			sizeCondition += condition + " OR ";
		}
		
		sizeCondition = sizeCondition.substring(0, sizeCondition.length() - 4);
		sizeCondition += ")";
		// End sizeCondition
		
		String sortByCondition = "";
		switch(sortBy) {
		case 1:
			sortByCondition = "ORDER BY product_name";
			break;
		case 2:
			sortByCondition = "ORDER BY product_name DESC";
			break;
		case 3:
			sortByCondition = "ORDER BY current_price";
			break;
		case 4:
			sortByCondition = "ORDER BY current_price DESC";
			break;
		}
		
		return "SELECT * FROM Product WHERE product_id IN "
				+ "(SELECT DISTINCT p.product_id FROM Product P\n"
				+ "JOIN Category C ON p.category_id = c.category_id\n"
				+ "JOIN Stock S ON p.product_id = s.product_id" + " WHERE "
				+ priceCondition + " AND " + categoryCondition + " AND "
				+ sizeCondition + " AND " + nameCondition + ") " + sortByCondition;
		
	}
	
	
	
}
