package com.mido.repositories;

import com.mido.models.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AdvertisementRepository {

    public void addAdvertisement(Advertisement ad) {
        //implementation
    }

    public void editAdvertisement(Advertisement ad) {
        //implementation
    }

    public void deleteAdvertisement(Integer id) {
        //implementation
    }

    public Advertisement findById(Integer id) {
        return new Advertisement();
    }

    public List<Advertisement> searchAdvertisements(String country, String city, String breed, Integer age, String gender) {
        return null;
    }
}
