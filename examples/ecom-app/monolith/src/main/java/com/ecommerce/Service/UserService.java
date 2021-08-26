package com.ecommerce.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.Dto.UserDto;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired UserRepository userRepository;
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username (email) not found");
		}
		
		return new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(), true, 
				true, true, true, getAuthorities(user.getRoles()));
		
	}
	
	private static List<GrantedAuthority> getAuthorities (String roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : Arrays.asList(roles.split(","))) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
	
	public User registerNewUser(UserDto userDto) throws RuntimeException {
		
		if(emailExists(userDto.getEmail())) {
			throw new RuntimeException("User already exists");
		}
		
		User user = new User( userDto.getEmail(),
							  bCryptPasswordEncoder.encode(userDto.getPassword()),
							  "ROLE_USER" );
		
		return userRepository.save(user);
		
	}
	
	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
	
}
