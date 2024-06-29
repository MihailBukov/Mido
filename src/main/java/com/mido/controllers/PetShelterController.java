package com.mido.controllers;

import com.mido.dtos.PetShelterDto;
import com.mido.services.PetShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/pet-shelter")
public class PetShelterController {

    private final PetShelterService petShelterService;

    @GetMapping("/{id}")
    public ResponseEntity<PetShelterDto> getPetShelter(@PathVariable long id) {
        return new ResponseEntity<>(petShelterService.getPetShelterById(id),  HttpStatus.OK);
    }
}
