package com.HelpdeskDemo.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "USER_VERIFICATION_TBL")
@AllArgsConstructor
@NoArgsConstructor
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String otp;
    private LocalDateTime expirationOtpTime;
}
