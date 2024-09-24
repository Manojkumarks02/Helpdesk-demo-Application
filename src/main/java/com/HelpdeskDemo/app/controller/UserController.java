package com.HelpdeskDemo.app.controller;

import com.HelpdeskDemo.app.dto.SignUpDto;
import com.HelpdeskDemo.app.dto.UserDto;
import com.HelpdeskDemo.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> initiateRegistration(@RequestBody SignUpDto signUpDto){
        UserDto userDto = userService.initiateRegistration(signUpDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<UserDto> completeRegistration(@RequestParam("email") String email,
                                                        @RequestParam("otp") String otp){
        UserDto userDto = userService.completeRegistration(email, otp);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
