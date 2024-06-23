package com.mido.repositories;

import com.mido.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a FROM Advertisement a WHERE " +
            "(:country IS NULL OR a.country = :country) AND " +
            "(:city IS NULL OR a.city = :city) AND " +
            "(:breed IS NULL OR a.dogBreed = :breed) AND " +
            "(:age IS NULL OR a.dogAge = :age) AND " +
            "(:gender IS NULL OR a.dogGender = :gender)")
    List<Advertisement> searchAdvertisements(@Param("country") String country, @Param("city") String city,
                                             @Param("breed") String breed, @Param("age") Integer age,
                                             @Param("gender") String gender);
}
