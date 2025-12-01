package com.hrms.service.implementation;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
