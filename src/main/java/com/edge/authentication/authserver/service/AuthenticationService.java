package com.edge.authentication.authserver.service;

import com.edge.authentication.authserver.pojo.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    JWTTokenService jwtTokenService;

    public AuthenticationResponse getAuthenticationResponse(String encryptedLocation) {
        return AuthenticationResponse.builder()
                .firstIp("10.101.11.1")
                .secondIp("10.101.12.2")
                .token(jwtTokenService.createToken())
                .build();
    }
}
