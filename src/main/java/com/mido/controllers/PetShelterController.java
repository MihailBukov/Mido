package com.mido.controllers;

import com.mido.dtos.PetShelterDto;
import com.mido.services.PetShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/pet-shelter")
public class PetShelterController {

    private final PetShelterService petShelterService;

    @GetMapping("/{username}")
    public ResponseEntity<PetShelterDto> getPetShelter(@PathVariable String username) {
        return new ResponseEntity<>(petShelterService.getPetShelterByUsername(username),  HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<PetShelterDto> putPetShelter(@PathVariable String username, @RequestBody PetShelterDto petShelterDto) {
        return new ResponseEntity<>(petShelterService.updatePetShelter(username, petShelterDto), HttpStatus.OK);
    }
}
