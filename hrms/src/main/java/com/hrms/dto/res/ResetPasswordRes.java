package com.hrms.dto.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordRes {

    private String message;

    public ResetPasswordRes(String message) {
        this.message = message;
    }
}

