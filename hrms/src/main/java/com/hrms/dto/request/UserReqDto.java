package com.hrms.dto.request;

import com.hrms.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReqDto {
	
	@NotNull(message = "First Name Required")
	private String firstName;
	
	@NotNull(message = "Last Name Required")
	private String lastName;
	
	@Email
	@NotNull(message = "Email required")
	private String email;
	
	@NotNull(message = "Password Required")
	private String password;
	
	@NotNull(message = "Role Required")
	private Role role;
}
