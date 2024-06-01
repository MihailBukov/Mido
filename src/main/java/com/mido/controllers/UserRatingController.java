package com.mido.controllers;

import com.mido.dtos.UserRatingDto;
import com.mido.models.UserRating;
import com.mido.services.UserRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class UserRatingController {
    private final UserRatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<UserRatingDto> createUserRating(@RequestBody UserRatingDto ratingDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRatingDto> updateRating(@PathVariable Integer id, @RequestBody UserRatingDto ratingDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRatingDto> deleteRating(@PathVariable Integer id) {
        ratingService.removeUserRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
