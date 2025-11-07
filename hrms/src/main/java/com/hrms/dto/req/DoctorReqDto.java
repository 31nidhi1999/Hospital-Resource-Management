package com.hrms.dto.req;

import com.hrms.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorReqDto {
	
	@NotNull(message = "First Name Required")
	private String firstName;
	
	@NotNull(message = "Last Name Required")
	private String lastName;
	
	@Email(message = "Email is Required")
	private String email;
	
	@NotNull(message = "Password is Required")
	private String password;
	
	private Role role = Role.DOCTOR;
	
	@NotNull(message = "Specialization is Required")
	private String specialization;
	
	@NotNull(message = "License Number is Required")
	private String licenseNumber;
}
