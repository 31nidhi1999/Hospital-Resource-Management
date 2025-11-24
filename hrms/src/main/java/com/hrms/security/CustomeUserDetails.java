package com.hrms.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hrms.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomeUserDetails implements UserDetails {
	
	@Autowired
	private User user;

	public CustomeUserDetails(User user2) {
        this.user = user2;
        log.info("CustomUserDetails created for user: {}", user2.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole().name();
        log.debug("Fetching authorities for user: {}, role: {}", user.getEmail(), role);
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
    	log.debug("Fetching password for user: {}", user.getEmail());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
    	log.debug("Fetching username (email) for user: {}", user.getEmail());
        return user.getEmail();
    }

    public User getUser() {
    	log.debug("Returning User object for: {}", user.getEmail());
        return user;
    }

}
