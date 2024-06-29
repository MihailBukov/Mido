package com.mido.repositories;

import com.mido.models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {

    List<UserRating> findByUserRated_Username(String username);
}
