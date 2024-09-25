package com.HelpdeskDemo.app.service.impl;

import com.HelpdeskDemo.app.dto.LoginDto;
import com.HelpdeskDemo.app.entity.User;
import com.HelpdeskDemo.app.repository.UserRepository;
import com.HelpdeskDemo.app.service.AuthService;
import com.HelpdeskDemo.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginDto loginDto) {
        User userEntity = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> new RuntimeException()
        );
        if(!userEntity.isUserVerified()){
            throw new RuntimeException("user email is not verified");
        }

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();

        String token = jwtService.generateToken(user);

        return token;
    }
}
