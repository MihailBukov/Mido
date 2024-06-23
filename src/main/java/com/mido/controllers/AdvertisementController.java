package com.mido.controllers;

import com.mido.dtos.AdvertisementDto;
import com.mido.dtos.requests.AdvertisementRequest;
import com.mido.mappers.AdvertisementMapper;
import com.mido.models.Advertisement;
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

    private final AdvertisementMapper adMapper;

    @GetMapping()
    public ResponseEntity<List<AdvertisementDto>> searchForAdvertisement(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender) {

        List<AdvertisementDto> ads = adService.searchAdvertisements(country, city, breed, age, gender);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createAdvertisement(@RequestBody AdvertisementRequest adReq) {
        adService.createAdvertisement(adReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementRequest adReq) {
        AdvertisementDto adDto = adService.editAdvertisement(id, adReq);
        return new ResponseEntity<>(adDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable Long id) {
        Advertisement ad = adService.findById(id);

        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdvertisementDto adDto = adMapper.adToAdDto(ad);
        return new ResponseEntity<>(adDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        adService.removeAdvertisement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
