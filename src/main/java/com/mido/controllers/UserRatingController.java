package com.mido.controllers;

import com.mido.dtos.UserRatingDto;
import com.mido.dtos.requests.UserRatingRequest;
import com.mido.services.UserRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class UserRatingController {
    private final UserRatingService ratingService;

    @GetMapping("/{username}")
    public ResponseEntity<List<UserRatingDto>> getUserRating(@PathVariable String username) {
        return new ResponseEntity<>(ratingService.getUserRatingsByUserUsername(username), HttpStatus.OK);
    }

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
