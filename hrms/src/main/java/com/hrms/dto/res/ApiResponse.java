package com.hrms.dto.res;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	
	private String message;
	private LocalDateTime dateTime;
	
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.dateTime = LocalDateTime.now();
	}
	
}
	