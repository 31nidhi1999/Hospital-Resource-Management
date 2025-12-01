package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.hrms.dto.req.VerifyOtpReq;
import com.hrms.dto.res.ForgetPasswordRes;
import com.hrms.dto.res.ResetPasswordRes;
import com.hrms.dto.res.SignInResDto;
import com.hrms.dto.res.VerifyOtpRes;
import com.hrms.security.JwtUtils;
import com.hrms.service.implementation.ForgetPasswordImpl;

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
	
	@Autowired
	private ForgetPasswordImpl service;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginser(@Valid @RequestBody SignInReqDto dto){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
		Authentication authenticate = authenticationManager.authenticate(token);
		String jwtToken = jwtUtils.generateJwtToken(authenticate);
		SignInResDto signInResDto = new SignInResDto(jwtToken, "Succesfully login");
		System.out.println(signInResDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(signInResDto);
	}
	
	@PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgetPasswordReq req) {
		try {
	        ForgetPasswordRes res = service.forgotPassword(req);
	        return ResponseEntity.ok(res);

	    } catch (Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Server error: " + e.getMessage());
	    }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<VerifyOtpRes> verifyOtp(@RequestBody VerifyOtpReq req) {
    	VerifyOtpRes res = service.verifyOtp(req);

        if (!res.isValid()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
        return ResponseEntity.ok(service.verifyOtp(req));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordRes> resetPassword(@RequestBody ResetPasswordReq req) {
        return ResponseEntity.ok(new ResetPasswordRes(jwtUtils.resetPassword(req)));
    }
}
