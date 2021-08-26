package com.ecommerce.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.Entity.Product;
import com.ecommerce.Service.AuthService;
import com.ecommerce.Service.BasketService;
import com.ecommerce.Service.ProductService;

@Controller
public class ProductDetailsController {

	@Autowired ProductService productService;
	@Autowired AuthService authService;
	@Autowired BasketService basketService;
	
	@GetMapping("/product-details/{productId}")
	public String showProductDetails(@PathVariable Long productId, Model model,
			HttpServletRequest request, Principal principal) {
		
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		boolean isOutOfStock = productService.isOutOfStockByProductId(productId);
		model.addAttribute("isOutOfStock", isOutOfStock);

		List<Product> relatedProducts = productService.getRelatedProductsByProductId(productId);
		model.addAttribute("relatedProducts", relatedProducts);
		
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
		
		return "product-details";
				
	}
	
	
	@PostMapping("/product-details/{productId}")
	public String addProductToBasket(Principal principal, @PathVariable Long productId, Model model,
			@RequestParam(value = "sizeSelect") String sizeSelect,
			@RequestParam(value = "qtySelect") Integer quantity) {
			
			Product product = productService.getProductById(productId);
			model.addAttribute("product", product);
			
			boolean isOutOfStock = productService.isOutOfStockByProductId(productId);
			model.addAttribute("isOutOfStock", isOutOfStock);
	
			List<Product> relatedProducts = productService.getRelatedProductsByProductId(productId);
			model.addAttribute("relatedProducts", relatedProducts);
			
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
		
			try {
				basketService.addProductToBasket(principal, sizeSelect, productId, quantity);
				model.addAttribute("productSuccessfullyAdded", true);
			} catch (Exception e) {
				model.addAttribute("productSuccessfullyAdded", false);
				
				System.out.println("Exception occurred while adding product to the basket");
				System.out.println("Most likely duplicate key in basket table");
				System.out.println("In other words, product/size pair already exists in user's basket");
			}
			
			// return to the page no matter the transaction were successful or not
			// either case, a model attribute will transferred to frontend via thymeleaf
			return "product-details";
			
	}
	
	
	
}
