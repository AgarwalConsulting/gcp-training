package com.ecommerce.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.Entity.Product;
import com.ecommerce.Service.AuthService;
import com.ecommerce.Service.BasketService;
import com.ecommerce.Service.ProductService;

@Controller
public class HomeController {
	
	@Autowired ProductService productService;
	@Autowired BasketService basketService;
	@Autowired AuthService authService;
	
	@GetMapping({"/","/index"})
	public String index(Model model, Principal principal) {
	
		model.addAttribute("recentSuits", productService.getRecentProducts("Suits"));
		model.addAttribute("recentBlazers", productService.getRecentProducts("Blazers"));
		model.addAttribute("recentShirts", productService.getRecentProducts("Shirts"));
		model.addAttribute("recentTrousers", productService.getRecentProducts("Trousers"));
		
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
		
		return "index";
	}

}
