package com.ecommerce.Service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public boolean isLoggedIn() {
		return SecurityContextHolder.getContext().getAuthentication() != null &&
			   SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
			   !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}
	
}
