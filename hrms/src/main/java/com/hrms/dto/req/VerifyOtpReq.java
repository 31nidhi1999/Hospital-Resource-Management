package com.hrms.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyOtpReq {
    @NotBlank
    private String email;

    @NotBlank
    private String otp;

    @NotBlank
    private String otpToken;
}
