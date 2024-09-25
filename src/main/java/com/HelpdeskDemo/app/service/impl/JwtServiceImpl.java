package com.HelpdeskDemo.app.service.impl;

import com.HelpdeskDemo.app.entity.User;
import com.HelpdeskDemo.app.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;


@Component
public class JwtServiceImpl implements JwtService {

    private String secretKey = "uujcnskjs jncjalskj lallsknkms mmskmmms dncajjks ajsiasnmkask mncajshxgsvaskjs";

    private SecretKey getSecretKey(){
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(User user) {
       return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("roles", Set.of("ADMIN", "USER", "SUPER_ADMIN"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSecretKey())
                .compact();
    }
}
