package com.hrms.dto.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyOtpRes {

    private boolean valid;
    private String message;

    public VerifyOtpRes(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}

