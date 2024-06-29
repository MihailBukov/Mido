package com.mido.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mido.dtos.requests.AuthenticationRequest;
import com.mido.dtos.requests.RegisterRequest;
import com.mido.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final ObjectMapper objectMapper;

    @PostMapping(path = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> registerFirstStep(@RequestPart String request,
                                                  @RequestPart(required = false) MultipartFile picture) throws IOException {
        RegisterRequest registerRequest = objectMapper.readValue(request, RegisterRequest.class);
        authenticationService.registerUser(registerRequest, picture);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        String jwt = authenticationService.authenticate(request);
        Cookie cookie = new Cookie("access_token", jwt);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 2); // 2 hours
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
