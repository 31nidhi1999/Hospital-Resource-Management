package com.hrms.dto.res;

import com.hrms.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientResDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
	private Integer age;
	private String address;
}
