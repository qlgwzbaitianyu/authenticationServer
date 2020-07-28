package com.edge.authentication.authserver.controller;

import com.edge.authentication.authserver.pojo.AuthenticationResponse;
import com.edge.authentication.authserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retrieve")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(value = "/token")
    public AuthenticationResponse getAuthenticationResponse(@RequestParam(name = "location") String encryptedLocation) {

        return authenticationService.getAuthenticationResponse(encryptedLocation);
    }

    @GetMapping(value = "/token/verify")
    public Boolean verifyToken(@RequestParam(name = "token") String token) throws Exception {

        return authenticationService.validateToken(token);
    }
}
