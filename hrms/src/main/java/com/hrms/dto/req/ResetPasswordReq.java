package com.hrms.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordReq {

    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String otpToken;
}

