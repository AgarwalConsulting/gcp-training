package com.ecommerce.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.Dto.BillingDto;
import com.ecommerce.Entity.Product;
import com.ecommerce.Service.BasketService;
import com.ecommerce.Service.CheckoutService;

@Controller
public class CheckoutController {

	@Autowired BasketService basketService;
	@Autowired CheckoutService checkoutService;
	
	@GetMapping("/checkout")
	public String getCheckout(Model model, Principal principal, HttpSession session) {
		
		BillingDto billingDto = new BillingDto();
		model.addAttribute("billingDto", billingDto);
		
		List<Product> basketItems = basketService.getCompactBasketByPrincipal(principal);
		model.addAttribute("basketItems", basketItems);
		
		Integer basketTotal = basketService.getBasketTotalByPrincipal(principal);
		model.addAttribute("basketTotal", basketTotal);
		
		boolean isBasketEmpty = basketService.isBasketEmpty(principal);
		model.addAttribute("isBasketEmpty", isBasketEmpty);
		
		// Needed for thymeleaf conditional
		// Permitted to logged in users only, so no need to refer to AuthService
		model.addAttribute("isLoggedIn", true);
		
		// Operations to do if payment attempt failed
		String errorMsg = (String) session.getAttribute("errorMsg");
		if(errorMsg != null) {
			model.addAttribute("errorMsg", errorMsg);
			session.removeAttribute("errorMsg"); 
		}
		
		return "checkout";
	}
	
	@PostMapping("/checkout")
	public void postCheckout(HttpServletResponse response, Principal principal,
			@ModelAttribute("billingDto") @Valid BillingDto billingDto, HttpSession session) {
		
		boolean paymentFailed = !(checkoutService.checkout(principal, billingDto));
		
		if(paymentFailed) {
			session.setAttribute("errorMsg", "Payment failed. Please try again.");
			try { response.sendRedirect("/checkout"); } catch (Exception e) { }
		}

		else {
			try { response.sendRedirect("/"); } catch (Exception e) { }
		}
		
	}
	
	
}
