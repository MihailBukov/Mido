package com.mido.controllers;

import com.mido.dtos.ClientDto;
import com.mido.dtos.PetShelterDto;
import com.mido.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{username}")
    public ResponseEntity<ClientDto> getClient(@PathVariable String username) {
        return new ResponseEntity<>(clientService.getClientByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<ClientDto> putPetShelter(@PathVariable String username, @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.updateClient(username, clientDto), HttpStatus.OK);
    }
}
