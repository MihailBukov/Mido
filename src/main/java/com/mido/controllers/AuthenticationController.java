package com.mido.controllers;

import com.mido.dtos.requests.AuthenticationRequest;
import com.mido.dtos.requests.ClientRegisterRequest;
import com.mido.dtos.requests.FirstStepRegister;
import com.mido.dtos.requests.PetShelterRegisterRequest;
import com.mido.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
