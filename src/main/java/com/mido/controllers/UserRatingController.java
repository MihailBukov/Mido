package com.mido.controllers;

import com.mido.dtos.UserRatingDto;
import com.mido.dtos.requests.UserRatingRequest;
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

    @PostMapping()
    public ResponseEntity<Void> createUserRating(@RequestBody UserRatingRequest ratingReq) {
        ratingService.addUserRating(ratingReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRatingDto> updateRating(@PathVariable Long id, @RequestBody UserRatingRequest ratingReq) {
        UserRatingDto rating = ratingService.editUserRating(id, ratingReq);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRatingDto> deleteRating(@PathVariable Long id) {
        ratingService.removeUserRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
