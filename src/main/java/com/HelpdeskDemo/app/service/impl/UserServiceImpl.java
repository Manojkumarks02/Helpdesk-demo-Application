package com.HelpdeskDemo.app.service.impl;

import com.HelpdeskDemo.app.dto.SignUpDto;
import com.HelpdeskDemo.app.dto.UserDto;
import com.HelpdeskDemo.app.entity.EmployeeEmail;
import com.HelpdeskDemo.app.entity.User;
import com.HelpdeskDemo.app.entity.UserVerification;
import com.HelpdeskDemo.app.repository.EmployeeEmailRepository;
import com.HelpdeskDemo.app.repository.UserRepository;
import com.HelpdeskDemo.app.repository.UserVerificationRepository;
import com.HelpdeskDemo.app.service.OtpService;
import com.HelpdeskDemo.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeEmailRepository employeeEmailRepository;
    private final ModelMapper modelMapper;
    private final OtpService otpService;
    private final UserVerificationRepository userVerificationRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException()
        );
    }

    @Override
    public UserDto initiateRegistration(SignUpDto signUpDto) {

        checkCompanyEmail(signUpDto.getEmail()); //Step 1: to check for the email is register for the company(company email)
        checkIfAlreadyUserRegistered(signUpDto.getEmail()); // step2: to check if the User is already registered.
        User userEntity = modelMapper.map(signUpDto, User.class);
        userEntity.setUserVerified(false);
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User savedUser = userRepository.save(userEntity);
        //sending the otp
        otpService.generateAndSendOTP(signUpDto.getEmail());
        return modelMapper.map(savedUser, UserDto.class);

    }

   @Override
    public UserDto completeRegistration(String email, String otp){
        UserVerification userVerification = userVerificationRepository.findByEmail(email).orElseThrow(() -> new RuntimeException());
         return validateOtp(userVerification,otp);

    }

    public  UserDto validateOtp(UserVerification userVerification, String otp){

        if(userVerification.getOtp().equals(otp) && userVerification.getExpirationOtpTime().isAfter(LocalDateTime.now())){
            User user = userRepository.findByEmail(userVerification.getEmail()).orElseThrow(() -> new RuntimeException("email not found"));
            user.setUserVerified(true);
            User savedUser = userRepository.save(user);
            userVerificationRepository.delete(userVerification);
            return modelMapper.map(savedUser, UserDto.class);
        }
        throw new IllegalArgumentException("Invalid otp");
    }

    public void checkCompanyEmail(String email) {
        Optional<EmployeeEmail> employee = employeeEmailRepository.findByEmail(email);
        if (!employee.isPresent()) {
            throw new IllegalArgumentException("This email is not associated with the company..!");
        }
    }

    public void checkIfAlreadyUserRegistered(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new IllegalArgumentException("User with this email :" + email + " already register...!");
        }
    }


}
