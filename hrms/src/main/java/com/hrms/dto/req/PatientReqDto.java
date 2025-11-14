package com.hrms.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hrms.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PatientReqDto {
	@NotBlank(message = "First Name Required")
	private String firstName;
	
	@NotBlank(message = "Last Name Required")
	private String lastName;
	
	@Email(message = "Email is Required")
	private String email;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Role role = Role.PATIENT;
	
	@NotBlank(message = "Password is Required")
	private String password;
	
	 @NotNull(message = "Age is required")
	private Integer age;
	
	@NotBlank(message = "Addriss is Required")
	private String address;
}
