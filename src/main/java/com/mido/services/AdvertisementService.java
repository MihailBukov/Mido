package com.mido.services;

import com.mido.models.Advertisement;
import com.mido.models.User;
import com.mido.repositories.AdvertisementRepository;
import com.mido.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository adRepo;

    public Advertisement createAdvertisement(Advertisement ad) {
        adRepo.addAdvertisement(ad);
        return ad;
    }

    public Advertisement findById(Integer id) {
        return adRepo.findById(id);
    }

    public Advertisement editAdvertisement(Advertisement ad) {
        adRepo.editAdvertisement(ad);
        return ad;
    }

    public void removeAdvertisement(Integer id) {
        adRepo.deleteAdvertisement(id);
    }

    public List<Advertisement> searchAdvertisements(String country, String city, String breed, Integer age, String gender){
        return adRepo.searchAdvertisements(country, city, breed, age, gender);
    }
}