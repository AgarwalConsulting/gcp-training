package com.ecommerce.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.Dto.UserDto;
import com.ecommerce.Service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired UserService userService;

	@GetMapping("/registration")
	public String showRegistrationForm(Model model, HttpSession session) {
		
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
	
		// required for navbar thymeleaf conditional
		model.addAttribute("isLoggedIn", false);
		
		// Operations to do if registration attempt failed
		String errorMsg = (String) session.getAttribute("errorMsg");
		if(errorMsg != null) {
			model.addAttribute("errorMsg", errorMsg);
			session.removeAttribute("errorMsg"); 
		}
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public void registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, 
			Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
				
		try { // user is successfully registered and redirected to the homepage
			userService.registerNewUser(userDto);
			request.login(userDto.getEmail(), userDto.getPassword());
			response.sendRedirect("");
		} catch (RuntimeException | ServletException | IOException e) {
			session.setAttribute("errorMsg", "Email address already exists.");
			try { response.sendRedirect("/registration"); } catch (Exception ex) {};
		} 
	}
	
}
