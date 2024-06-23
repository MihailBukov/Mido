package com.mido.services;

import com.mido.dtos.AdvertisementDto;
import com.mido.dtos.requests.AdvertisementRequest;
import com.mido.mappers.AdvertisementMapper;
import com.mido.models.Advertisement;
import com.mido.models.User;
import com.mido.repositories.AdvertisementRepository;
import com.mido.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository adRepo;

    private final AdvertisementMapper adMapper;

    public void createAdvertisement(AdvertisementRequest adReq) {
        Advertisement ad = adMapper.advertisementCreateRequestToAdvertisement(adReq);
        ad.setTimeOfCreation(LocalDateTime.now());
        adRepo.saveAndFlush(ad);
    }

    public Advertisement findById(Long id) {
        return adRepo.findById(id).orElse(null);
    }

    public AdvertisementDto editAdvertisement(Long id, AdvertisementRequest adReq) {
        Advertisement ad = adMapper.advertisementCreateRequestToAdvertisement(adReq);
        Optional<Advertisement> optionalAd = adRepo.findById(id);
        Advertisement adToEdit = getAdvertisement(optionalAd, ad);
        adRepo.saveAndFlush(adToEdit);
        return adMapper.adToAdDto(adToEdit);
    }

    private static Advertisement getAdvertisement(Optional<Advertisement> optionalAd, Advertisement ad) {
        if (optionalAd.isPresent()) {
            Advertisement adToEdit = optionalAd.get();

            if (ad.getTitle() != null) {
                adToEdit.setTitle(ad.getTitle());
            }
            if (ad.getTimeOfCreation() != null) {
                adToEdit.setTimeOfCreation(ad.getTimeOfCreation());
            }
            if (ad.getDescription() != null) {
                adToEdit.setDescription(ad.getDescription());
            }
            if (ad.getDogName() != null) {
                adToEdit.setDogName(ad.getDogName());
            }
            if (ad.getDogAge() != null) {
                adToEdit.setDogAge(ad.getDogAge());
            }
            if (ad.getDogKg() != null) {
                adToEdit.setDogKg(ad.getDogKg());
            }
            if (ad.getDogBreed() != null) {
                adToEdit.setDogBreed(ad.getDogBreed());
            }
            if (ad.getDogGender() != null) {
                adToEdit.setDogGender(ad.getDogGender());
            }
            if (ad.getDogColor() != null) {
                adToEdit.setDogColor(ad.getDogColor());
            }

            return adToEdit;
        }
        else {
            throw new IllegalArgumentException("Advertisement not found");
        }
    }

    public void removeAdvertisement(Long id) {
        adRepo.deleteById(id);
    }

    public List<AdvertisementDto> searchAdvertisements(String country, String city, String breed, Integer age, String gender){
        List<Advertisement> ads = adRepo.searchAdvertisements(country, city, breed, age, gender);
        return ads.stream().map(adMapper :: adToAdDto).collect(Collectors.toList());
    }
}
