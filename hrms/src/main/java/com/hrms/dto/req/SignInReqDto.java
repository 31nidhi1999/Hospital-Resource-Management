package com.hrms.dto.req;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInReqDto {
	
	@NotBlank
	private String userName;
	
	@NotBlank
	@Length(max = 20, min = 5)
	private String password;
}
