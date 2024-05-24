package com.mido.controlers;

import com.mido.dtos.UserRatingDto;
import com.mido.mappers.UserRatingMapper;
import com.mido.models.UserRating;
import com.mido.services.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRatingControler {


    @Autowired
    private UserRatingMapper ratingMapper;

    @Autowired
    private UserRatingService ratingService;

    @PostMapping("/api/rating")
    public ResponseEntity<UserRatingDto> createUserRating(@RequestBody UserRatingDto ratingDto) {
        UserRating userRating = ratingMapper.mapFromDto(ratingDto);
        return new ResponseEntity<>(ratingMapper.mapToDto(ratingService.addUserRating(userRating)), HttpStatus.CREATED);
    }

    @PatchMapping("/api/rating/{id}")
    public ResponseEntity<UserRatingDto> updateRating(@PathVariable Integer id, @RequestBody UserRatingDto ratingDto) {
        UserRating userRating = ratingMapper.mapFromDto(ratingDto);
        userRating.setId(id); //not sure about this line
        return new ResponseEntity<>(ratingMapper.mapToDto(ratingService.editUserRating(userRating)), HttpStatus.OK);
    }

    @DeleteMapping("/api/rating/{id}")
    public ResponseEntity<UserRatingDto> deleteRating(@PathVariable Integer id) {
        ratingService.removeUserRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
