package com.hrms.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hrms.custom_exceptions.ApiException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("Your OTP for Password Reset");
        msg.setText("Your OTP is: " + otp + "\n\nOTP is valid for 5 minutes.");
        
        try {
        	mailSender.send(msg);
        } catch (Exception e) {
            throw new ApiException(
                "MAIL_SENDING_FAILED"
            );
        }


        
    }
}

