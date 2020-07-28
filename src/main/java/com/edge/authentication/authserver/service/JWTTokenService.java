package com.edge.authentication.authserver.service;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenService {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    private static final String CHARSET_UTF_8 = "UTF-8";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.timeout}")
    private String timeout;

    @Value("${jwt.username}")
    private String username;

    private String secretKeyInBytes;
    private long validityInMilliseconds;

    @PostConstruct
    protected void initJwtParameters() {
        secretKeyInBytes = Base64.getEncoder().encodeToString(secretKey.getBytes(Charset.forName(CHARSET_UTF_8)));
        validityInMilliseconds = Long.parseLong(timeout);
    }

//    public HttpHeaders buildAuthenticationHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        String token = createToken();
//        headers.add(AUTHORIZATION, BEARER + StringUtils.SPACE + token);
//        return headers;
//    }

    public String createToken() {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKeyInBytes)
                .compact();
    }

}