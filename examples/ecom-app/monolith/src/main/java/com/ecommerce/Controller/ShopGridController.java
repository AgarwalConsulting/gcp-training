package com.ecommerce.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.Dto.FilterDto;
import com.ecommerce.Entity.Product;
import com.ecommerce.Service.AuthService;
import com.ecommerce.Service.BasketService;
import com.ecommerce.Service.FilterService;
import com.ecommerce.Service.ProductService;

@Controller
@RequestMapping("/shop-grid")
public class ShopGridController {
	
	// TODO Ditch the requestparams and use a FilterDto
	
	@Autowired FilterService filterService;
	@Autowired ProductService productService;
	@Autowired AuthService authService;
	@Autowired BasketService basketService;
	
	@GetMapping("/sq={dashedSearchString}&filter={filterString}")
	public String getShopGrid(@PathVariable String filterString,
							  @PathVariable String dashedSearchString, 
							  Model model, Principal principal) {
		
		FilterDto filterDto = filterService.decomposeFilterString(dashedSearchString, filterString);
		List<Product> filteredItems = productService.getFilteredProducts(filterDto);
		model.addAttribute("filteredItems", filteredItems);
		
		model.addAttribute("numberOfProductsFound", filteredItems.size());
		model.addAttribute("filterObject", filterDto);
		
		model.addAttribute("globalMinPrice", productService.getGlobalMinPrice());
		model.addAttribute("globalMaxPrice", productService.getGlobalMaxPrice());
		
		// Fetch login info / basket? for navbar cart
		boolean isLoggedIn = authService.isLoggedIn();
		model.addAttribute("isLoggedIn", isLoggedIn);
		
		if(isLoggedIn) {
			List<Product> basketItems = basketService.getCompactBasketByPrincipal(principal);
			model.addAttribute("basketItems", basketItems);
			
			Integer basketTotal = basketService.getBasketTotalByPrincipal(principal);
			model.addAttribute("basketTotal", basketTotal);
			
			boolean isBasketEmpty = basketService.isBasketEmpty(principal);
			model.addAttribute("isBasketEmpty", isBasketEmpty);
		}
		
		return "shop-grid";
	}
	
	
	
	// MAJOR TO DO: Ditch the requestparams and use a FilterDto
	@PostMapping("/sq={dashedSearchString}&filter={filterString}")
	public void postShopGrid(HttpServletResponse response,
			@RequestParam(value = "rawSearchString", required = false) String rawSearchString,
			@RequestParam(value = "Suits", required = false) String suits,
			@RequestParam(value = "Blazers", required = false) String blazers,
			@RequestParam(value = "Shirts", required = false) String shirts,
			@RequestParam(value = "Trousers", required = false) String trousers,
			@RequestParam(value = "s-size", required = false) String sSize,
			@RequestParam(value = "m-size", required = false) String mSize,
			@RequestParam(value = "l-size", required = false) String lSize,
			@RequestParam(value = "xl-size", required = false) String xlSize,
			@RequestParam(value = "xxl-size", required = false) String xxlSize,
			@RequestParam(value = "price", required = false) String priceRange,
			@RequestParam(value = "select", required = false) String sortBy) {
		

		String url = filterService.composeFilterString(rawSearchString, suits, blazers, 
			shirts, trousers, sSize, mSize, lSize, xlSize, xxlSize, priceRange, sortBy);
		
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // END OF PostMapping
	
	
	@PostMapping("/search-with-query")
	public void searchWithQuery(HttpServletResponse response,
				@RequestParam(value = "query", required = false) String rawSearchString) {
		
		String dashedSearchString = rawSearchString.replace(" ", "-");
		String url = "/shop-grid/sq=" + dashedSearchString + "&filter=0P100000P11234CS12345";
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // End of searchWithQuery PostMapping
	
}
