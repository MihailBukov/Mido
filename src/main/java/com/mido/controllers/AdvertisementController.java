package com.mido.controllers;

import com.mido.dtos.AdvertisementDto;
import com.mido.services.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService adService;

    @GetMapping()
    public ResponseEntity<List<AdvertisementDto>> searchForAdvertisement(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto adDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable Integer id, @RequestBody AdvertisementDto adDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
