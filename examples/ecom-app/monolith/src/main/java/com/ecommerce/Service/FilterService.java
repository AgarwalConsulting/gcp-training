package com.ecommerce.Service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.ecommerce.Dto.FilterDto;

@Service
public class FilterService {
	
	// Filter URL Structure 	(Default 0P10000P11234CS12345)
	// Price Range:				{min_price_integer}P{max_price_integer}P
	// Sort By (One Digit): 	1) A-Z	2) Z-A	3) Price Ascending	4) Price Descending	
	// Category (1-4 Digits):	1) Suits 2) Blazers 3) Shirts 4) Trousers	
	// Size (1-5 Digits):		CS + 1) S  2) M  3) L  4) XL  5) XXL
	
	public FilterDto decomposeFilterString(String dashedSearchString, String filter) {
		
		String rawSearchString = dashedSearchString.replace("-", " "); 
		
		String priceFilter = filter.substring(0, filter.indexOf("P", filter.indexOf("P") + 1));
		ArrayList<String> priceList = new ArrayList<String>(Arrays.asList(priceFilter.split("P")));
		Integer min_price = Integer.parseInt(priceList.get(0));
		Integer max_price = Integer.parseInt(priceList.get(1));
		
		String sortByStr = filter.substring(filter.indexOf("P", filter.indexOf("P") + 1) + 1 , 
										 filter.indexOf("P", filter.indexOf("P") + 1) + 2 );
		Integer sortBy = Integer.parseInt(sortByStr);
		
		String categoryFilter = filter.substring(filter.indexOf("P", filter.indexOf("P") + 1) + 2 , 
												 filter.indexOf("CS"));
		char[] categoryCharList = categoryFilter.toCharArray();
		
		ArrayList<Integer> categoryList = new ArrayList<Integer>();
		for(char cat : categoryCharList) {
			categoryList.add(Character.getNumericValue(cat));
		}
		
		String sizeFilter = filter.substring(filter.indexOf("CS") + 2);
		char[] sizeCharList = sizeFilter.toCharArray();
		
		ArrayList<Integer> sizeList = new ArrayList<Integer>();
		for(char size : sizeCharList) {
			sizeList.add(Character.getNumericValue(size));
		}
		
		return new FilterDto(rawSearchString, min_price, max_price, 
						  sortBy, categoryList, sizeList);
		
	} // End of decomposeFilterString method
	
	
	
	public String composeFilterString(String rawSearchString, String suits, 
		String blazers, String shirts, String trousers, String sSize, String mSize, 
		String lSize, String xlSize, String xxlSize, String priceRange, String sortBy) {
		
			String dashedSearchString = rawSearchString.replace(" ", "-");
			
			String url = "/shop-grid/sq=" + dashedSearchString +"&filter=";
			
			String[] priceLimits = priceRange.split(" - ");
			String minPrice = priceLimits[0].replace("$", "");
			String maxPrice = priceLimits[1].replace("$", "");
			
			url += minPrice + "P" + maxPrice + "P" + sortBy;
			
			if(suits != null) {
				url += "1";
			}
			if(blazers != null) {
				url += "2";
			}
			if(shirts != null) {
				url += "3";
			}
			if(trousers != null) {
				url += "4";
			}
			
			url += "CS";
			
			if(sSize != null) {
				url += "1";
			}
			if(mSize != null) {
				url += "2";
			}
			if(lSize != null) {
				url += "3";
			}
			if(xlSize != null) {
				url += "4";
			}
			if(xxlSize != null) {
				url += "5";
			}
			
			return url;
	} // End of composeFilterString method
	
}
