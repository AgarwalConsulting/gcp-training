package com.ecommerce.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoLoginCont {
	
	@GetMapping("/starthere")
	public void starthere(HttpServletResponse response, HttpServletRequest request) {
		
		try {
			request.login("yahya.toraman@boun.edu.tr", "123qwe");
		} catch (ServletException e) {
			System.out.println("user already logged in (test autologincontroller)");
		}
		
		try {
			response.sendRedirect("/checkout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
