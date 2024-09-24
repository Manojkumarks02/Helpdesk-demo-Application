package com.HelpdeskDemo.app.service.impl;

import com.HelpdeskDemo.app.entity.UserVerification;
import com.HelpdeskDemo.app.repository.UserVerificationRepository;
import com.HelpdeskDemo.app.service.EmailService;
import com.HelpdeskDemo.app.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final EmailService emailService;
    private final UserVerificationRepository userVerificationRepository;

    @Override
    public void generateAndSendOTP(String email) {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String otp = String.valueOf(100000 + randomNumber);

        //expirationtime for the OTP
        LocalDateTime expirationOtpTime = LocalDateTime.now().plusMinutes(10);


        UserVerification userVerification = new UserVerification();
        userVerification.setEmail(email);
        userVerification.setOtp(otp);
        userVerification.setExpirationOtpTime(expirationOtpTime);
        userVerificationRepository.save(userVerification);
        emailService.sendEmailOtp(email, otp);
    }
}
