package com.hrms.dto.res;

import com.hrms.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
}
