package com.hrms.dto.res;

import com.hrms.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorResDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
	private String specialization;
	private String licenseNumber;
}
