package com.hrms.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPasswordReq {
	@NotBlank(message = "Email is required")
    @Email
    private String email;
}
