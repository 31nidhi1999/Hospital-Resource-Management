package com.hrms.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignInResDto {
	private String jwt;
	private String msg;
	
	public SignInResDto(String jwt, String msg) {
		super();
		this.jwt = jwt;
		this.msg = msg;
	}
}
