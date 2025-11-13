package com.hrms.dto.req;

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
	
	@Email(message = "Email is Required")
	private String email;
	
	@NotNull(message = "Password is Required")
	private String password;
	
	private Role role = Role.ADMIN;
}
