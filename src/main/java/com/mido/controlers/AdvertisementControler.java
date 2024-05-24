package com.mido.controlers;

import com.mido.dtos.AdvertisementDto;
import com.mido.mappers.AdvertisementMapper;
import com.mido.models.Advertisement;
import com.mido.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdvertisementControler {

    @Autowired
    private AdvertisementService adService;

    @Autowired
    private AdvertisementMapper adMapper;

    @GetMapping("/api/advertisement")
    public ResponseEntity<List<AdvertisementDto>> searchForAdvertisement(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender) {

        List<Advertisement> ads = adService.searchAdvertisements(country, city, breed, age, gender);

        List<AdvertisementDto> adDtos = ads.stream()
                .map(adMapper::mapToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adDtos, HttpStatus.OK);
    }

    @PostMapping("/api/advertisement")
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto adDto) {
        Advertisement ad = adMapper.mapFromDto(adDto);
        return new ResponseEntity<>(adMapper.mapToDto(adService.createAdvertisement(ad)), HttpStatus.CREATED);
    }

    @PatchMapping("/api/advertisement/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable Integer id, @RequestBody AdvertisementDto adDto) {
        Advertisement ad = adMapper.mapFromDto(adDto);
        ad.setId(id);
        return new ResponseEntity<>(adMapper.mapToDto(adService.editAdvertisement(ad)), HttpStatus.OK);
    }

    @GetMapping("/api/advertisement/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable Integer id) {
        Advertisement ad = adService.findById(id);
        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AdvertisementDto adDto = adMapper.mapToDto(ad);
        return new ResponseEntity<>(adDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<AdvertisementDto> deleteAdvertisement(@PathVariable Integer id) {
        adService.removeAdvertisement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
