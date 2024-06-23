package com.mido.services;

import com.mido.dtos.UserRatingDto;
import com.mido.dtos.requests.UserRatingRequest;
import com.mido.mappers.UserRatingMapper;
import com.mido.models.User;
import com.mido.models.UserRating;
import com.mido.repositories.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRatingService {

    private final UserRatingRepository userRatingRepo;

    private final UserRatingMapper ratingMapper;

    private final UserService userService;

    public void addUserRating(UserRatingRequest ratingReq) {
        UserRating rating = setRatingFromRequest(ratingReq);
        userRatingRepo.saveAndFlush(rating);
    }

    public UserRatingDto editUserRating(Long id, UserRatingRequest ratingReq) {
        UserRating rating = setRatingFromRequest(ratingReq);
        Optional<UserRating> optionalRating = userRatingRepo.findById(id);
        UserRating editedRating = getEditedRating(optionalRating, rating);
        userRatingRepo.saveAndFlush(editedRating);
        return ratingMapper.ratingToRatingDto(editedRating);
    }

    private UserRating setRatingFromRequest(UserRatingRequest ratingReq) {
        UserRating rating = new UserRating();

        if(ratingReq.rating() < 1 || ratingReq.rating() > 5) {
            throw new IllegalArgumentException("Rating is not in the accurate interval");
        }

        rating.setRating(ratingReq.rating());

        if(ratingReq.userRates() == null || ratingReq.userRated() == null) {
            throw new IllegalArgumentException("Usernames are empty");
        }

        if(userService.loadUserByUsername(ratingReq.userRates()) == null ||
                userService.loadUserByUsername(ratingReq.userRated()) == null) {
            throw new IllegalArgumentException("Such user doesn't exist");
        }

        rating.setUserRates(userService.loadUserByUsername(ratingReq.userRates()));
        rating.setUserRated(userService.loadUserByUsername(ratingReq.userRated()));

        return rating;
    }

    private UserRating getEditedRating(Optional<UserRating> optionalRating, UserRating rating) {
        if(optionalRating.isPresent()) {
            UserRating newRating = optionalRating.get();

            if(rating.getRating() != null) {
                newRating.setRating(rating.getRating());
            }
            if(rating.getUserRates() != null) {
                newRating.setUserRates(rating.getUserRates());
            }
            if(rating.getUserRated() != null) {
                newRating.setUserRated(rating.getUserRated());
            }

            return newRating;
        } else {
            throw new IllegalArgumentException("Rating not found.");
        }
    }


    public void removeUserRating(Long id) {
        userRatingRepo.deleteById(id);
    }
}
