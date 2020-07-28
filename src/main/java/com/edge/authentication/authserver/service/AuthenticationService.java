package com.edge.authentication.authserver.service;

import com.edge.authentication.authserver.pojo.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public AuthenticationResponse getAuthencationResponse(String encryptedLocation) {
        return AuthenticationResponse.builder()
                .firstIp("10.101.11.1")
                .secondIp("10.101.12.2")
                .token("jwt token")
                .build();
    }
}
