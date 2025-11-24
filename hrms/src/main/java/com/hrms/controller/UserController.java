package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.req.SignInReqDto;
import com.hrms.dto.res.SignInResDto;
import com.hrms.security.JwtUtils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginser(@Valid @RequestBody SignInReqDto dto){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
		Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String jwtToken = jwtUtils.generateJwtToken(usernamePasswordAuthenticationToken);
		SignInResDto signInResDto = new SignInResDto(jwtToken, "Succesfully login");
		System.out.println(signInResDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(signInResDto);
	}
}
