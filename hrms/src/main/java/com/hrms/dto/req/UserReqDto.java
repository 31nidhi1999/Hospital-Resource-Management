package com.hrms.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hrms.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReqDto {
	@NotBlank(message = "First Name Required")
	private String firstName;
	
	@NotBlank(message = "Last Name Required")
	private String lastName;
	
	@Email(message = "Email is Required")
	private String email;
	
	@NotBlank(message = "Password is Required")
	private String password;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Role role = Role.ADMIN;
}
