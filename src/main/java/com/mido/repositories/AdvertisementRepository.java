package com.mido.repositories;

import com.mido.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a FROM Advertisement a WHERE " +
            "(:country = '' OR a.country = :country) AND " +
            "(:city = '' OR a.city = :city) AND " +
            "(:breed = '' OR a.dogBreed = :breed) AND " +
            "(a.dogAge = :age) AND " +
            "(:gender = '' OR a.dogGender = :gender)")
    List<Advertisement> searchAdvertisements(@Param("country") String country, @Param("city") String city,
                                             @Param("breed") String breed, @Param("age") Integer age,
                                             @Param("gender") String gender);
}
