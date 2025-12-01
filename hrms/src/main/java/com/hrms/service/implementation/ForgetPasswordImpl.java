package com.hrms.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrms.dto.req.ForgetPasswordReq;
import com.hrms.dto.req.ResetPasswordReq;
import com.hrms.dto.req.VerifyOtpReq;
import com.hrms.dto.res.ForgetPasswordRes;
import com.hrms.dto.res.ResetPasswordRes;
import com.hrms.dto.res.VerifyOtpRes;
import com.hrms.entity.User;
import com.hrms.repository.UserRepo;
import com.hrms.security.JwtUtils;

@Service
public class ForgetPasswordImpl{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtUtils jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ForgetPasswordRes forgotPassword(ForgetPasswordReq req) {

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpService.generateOtp();

        emailService.sendOtpEmail(req.getEmail(), otp);

        String otpToken = jwtUtil.generateOtpToken(req.getEmail(), otp);

        return new ForgetPasswordRes("OTP sent to your email", otpToken);
    }

    public VerifyOtpRes verifyOtp(VerifyOtpReq req) {

        boolean valid = jwtUtil.verifyOtp(req);

        if (valid) {
            return new VerifyOtpRes(true, "OTP verified successfully");
        } else {
            return new VerifyOtpRes(false, "Invalid or expired OTP");
        }
    }
}
