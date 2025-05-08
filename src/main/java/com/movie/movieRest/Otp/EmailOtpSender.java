package com.movie.movieRest.Otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpSender {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailOtpSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mahigowda416@gmail.com");
        message.setTo(email);
        message.setSubject("Your OTP for Movies Application Registration");
        message.setText("Your OTP is: " + otp + "\nThis code is valid for 10 minutes.");
        mailSender.send(message);

        System.out.println("OTP sent successfully");
    }
}
