package com.edge.authentication.authserver.service;

import com.edge.authentication.authserver.pojo.AuthenticationResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class AuthenticationService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    JWTTokenService jwtTokenService;

    public AuthenticationResponse getAuthenticationResponse(String encryptedLocation) {
        return AuthenticationResponse.builder()
                .firstIp("http://localhost:8081/edge/data")
                .secondIp("10.101.12.2")
                .token(jwtTokenService.createToken())
                .build();
    }

    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
            //throw new Exception("Expired or invalid JWT token");
        }
    }
}
