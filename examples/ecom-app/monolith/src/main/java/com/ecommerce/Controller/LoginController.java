package com.ecommerce.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.Dto.UserDto;
import com.ecommerce.Service.UserService;

@Controller
public class LoginController {

	@Autowired UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model, HttpSession session) {
		
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		
		// required for navbar thymeleaf conditional
		model.addAttribute("isLoggedIn", false);
		
		// Operations to do if login attempt failed
		String errorMsg = (String) session.getAttribute("errorMsg");
		if(errorMsg != null) {
			model.addAttribute("errorMsg", errorMsg);
			session.removeAttribute("errorMsg"); 
		}
		
		return "login";
	}

	
	@PostMapping("/login")
	public void loginUser(@ModelAttribute("user") @Valid UserDto userDto, 
			Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
				
		try { // user is successfully logged in and redirected to the homepage
			request.login(userDto.getEmail(), userDto.getPassword());
			response.sendRedirect("");
		} catch (RuntimeException | ServletException | IOException e) {
			session.setAttribute("errorMsg", "Email and password combination is not found.");
			try { response.sendRedirect("/login"); } catch (Exception ex) {}
		} 
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletResponse response) {
		SecurityContextHolder.getContext().setAuthentication(null);
		// then the user is redirected to the homepage
		// as specified in the SecurityConfiguration class
	}
	
}
