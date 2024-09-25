package com.HelpdeskDemo.app.service;

import com.HelpdeskDemo.app.entity.User;

public interface JwtService {

    String generateToken(User user);
}
