package com.mido.services;

import com.mido.models.UserRating;
import com.mido.repositories.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingService {

    private final UserRatingRepository userRatingRepository;

    public UserRating addUserRating(UserRating rating) {
        userRatingRepository.save(rating);
        return rating;
    }

    public UserRating editUserRating(UserRating rating) {
        userRatingRepository.save(rating);
        return rating;
    }

    //removeUserRating
    public void removeUserRating(Integer id) {
        //userRatingRepository.delete(id);
    }
}
