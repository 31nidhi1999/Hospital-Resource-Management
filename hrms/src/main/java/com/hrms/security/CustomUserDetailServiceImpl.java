package com.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrms.entity.User;
import com.hrms.repository.UserRepo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CustomUserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Attempting to load user by username: {}", username);
		
		User user = userRepo.findByEmail(username)
		.orElseThrow(()-> new UsernameNotFoundException("invalid email"));
		
		 log.info("User found: {} with role: {}", user.getEmail(), user.getRole());
		 
		return new CustomUseDetails(user);
	}

}
