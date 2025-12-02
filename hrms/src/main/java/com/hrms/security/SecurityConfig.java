package com.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
@EnableAsync
public class SecurityConfig {
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		log.info("Initializing AuthenticationManager...");
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
	log.info("Configuring SecurityFilterChain...");
	
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/users/signup",
	                "/users/login",
	                "/v3/api-docs/**",
	                "/v2/api-docs/**",
	                "/swagger-ui/**",
	                "/swagger-ui.html",
	                "/swagger-resources/**",
	                "/webjars/**",
	                "/api/users/login",
	                "/api/users/forgot-password",
	                "/api/users/verify-otp",
	                "/api/users/reset-password",
	                "/api/doctors/register",
	                "/api/patients/register",
	                "/api/admins/register"
	                
	            ).permitAll()
	            
	            .requestMatchers(HttpMethod.OPTIONS).permitAll()
	            .requestMatchers("/api/resources/register",
	            		"/api/resources/id/**",
	            		"/api/resources/update/**",
	            		"/api/resources/delete/**",
	            		"/api/doctors/delete/**",
	            		"/api/doctors/list",
	            		"/api/patients/delete/**",
	            		"/api/requests/list",
	            		"/api/requests/id/**",
	            		"/api/requests/id/*/approve",
	            		"/api/requests/id/*/reject",
	            		"/api/schedules/list",
	            		"/api/schedules/generate",
	            		"/api/admissions/create",
	            		"/api/admissions/id/**")
	            .hasAuthority("ADMIN")
	            .requestMatchers("/api/doctors/update/**",
	            		"/api/schedules/id/**",
	            		"/api/requests/create",
	            		"/api/treatments/create")
	            .hasAuthority("DOCTOR")
	            .requestMatchers("/api/patients/update/**")
	            .hasAuthority("PATIENT")
				.requestMatchers("/api/admissions/list", "/api/resources/list")
				.hasAnyAuthority("ADMIN", "DOCTOR")
				.requestMatchers("/api/treatments/list").hasAnyAuthority("ADMIN", "DOCTOR", "PATIENT")
				.requestMatchers("/api/patients/list").hasAnyAuthority("ADMIN", "DOCTOR")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form.disable())
	        .httpBasic(basic -> basic.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    	
	    log.info("SecurityFilterChain configured successfully.");
	    return http.build();
	}
}
