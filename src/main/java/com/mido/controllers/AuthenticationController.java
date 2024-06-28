package com.mido.controllers;

import com.mido.dtos.requests.AuthenticationRequest;
import com.mido.dtos.requests.ClientRegisterRequest;
import com.mido.dtos.requests.FirstStepRegister;
import com.mido.dtos.requests.PetShelterRegisterRequest;
import com.mido.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/first-step")
    public ResponseEntity<Void> registerFirstStep(@RequestBody FirstStepRegister request) {
        authenticationService.firstStepRegister(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register/client-register")
    public ResponseEntity<Void> registerClient(@RequestBody ClientRegisterRequest request) {
        authenticationService.registerClient(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register/pet-shelter-register")
    public ResponseEntity<Void> registerPetShelter(@RequestBody PetShelterRegisterRequest request) {
        authenticationService.registerPetShelter(request);
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
