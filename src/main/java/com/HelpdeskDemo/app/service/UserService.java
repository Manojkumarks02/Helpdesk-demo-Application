package com.HelpdeskDemo.app.service;

import com.HelpdeskDemo.app.dto.SignUpDto;
import com.HelpdeskDemo.app.dto.UserDto;

public interface UserService {

    UserDto initiateRegistration(SignUpDto signUpDto);

    UserDto completeRegistration(String email, String otp);
}
