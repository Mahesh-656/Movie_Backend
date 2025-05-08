package com.movie.movieRest.Otp;

import lombok.Data;

@Data
public class OtpVerificationDTO {
    private String email;
    private String otp;
}
