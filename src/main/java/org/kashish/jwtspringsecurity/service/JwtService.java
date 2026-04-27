package org.kashish.jwtspringsecurity.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String genrateToken() {
        return "token";

    }
}
