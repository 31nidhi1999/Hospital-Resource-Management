package com.hrms.dto.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPasswordRes {

    private String message;
    private String otpToken;

    public ForgetPasswordRes(String message, String otpToken) {
        this.message = message;
        this.otpToken = otpToken;
    }
}
