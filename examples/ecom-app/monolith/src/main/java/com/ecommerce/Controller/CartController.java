package com.ecommerce.Controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.Dto.BasketDto;
import com.ecommerce.Service.BasketService;

@Controller
public class CartController {
	
	@Autowired BasketService basketService;

	@GetMapping("/cart")
	public String cart(Model model, Principal principal, HttpServletRequest request) {
	
		BasketDto basketDto = basketService.getComprehensiveBasketByPrincipal(principal);
		model.addAttribute("basketDto", basketDto);;
		model.addAttribute("basketSize", basketDto.getItems().size());
		
		Integer basketTotal = basketService.getBasketTotalByPrincipal(principal);
		model.addAttribute("basketTotal", basketTotal);
		
		// Needed for thymeleaf conditional
		// Permitted to logged in users only, so no need to refer to AuthService
		model.addAttribute("isLoggedIn", true);

		// Basket details needed for NAVBAR CART
		model.addAttribute("basketItems", basketService.getCompactBasketByPrincipal(principal));
		model.addAttribute("isBasketEmpty", basketService.isBasketEmpty(principal));
		
		return "cart";
		
	} // END OF cart
	
	
	@PostMapping("/cart")
	public void updateOrProceed(Model model, HttpServletResponse response, Principal principal,
					@RequestParam(value = "quantities") String quantities,
					@RequestParam(value = "operation") String operation) {
		
		
		// To deal with variable number of products (hence quantity selects) in the user basket
		// A hidden form with two fields is communicated from frontend to backend
		// First is, quantities with a "/" delimiter
		// Second is, operation name which is either "update" or "checkout"
		
		basketService.updateBasket(principal, quantities.split("/"));
		
		if (operation.equals("checkout")) {
			try { response.sendRedirect("/checkout"); } catch (IOException e) { }
		} else if (operation.equals("update")) {
			try { response.sendRedirect("/cart"); } catch (IOException e) { }
		} else {
			System.out.println("this should really not be happening");
		}
	} 
	
	
	@RequestMapping("/delete-row/{sizeId}/{productId}")
	public void deleteRow(HttpServletResponse response, Principal principal,
			@PathVariable Long sizeId,
			@PathVariable Long productId) {
		
		basketService.deleteBasketRow(principal, sizeId, productId);
		try { response.sendRedirect("/cart"); } catch (IOException e)  { }
		
	}
	
	
	
}
