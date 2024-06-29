package com.mido.services;

import com.mido.models.PetShelter;
import com.mido.models.Role;
import com.mido.repositories.PetShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetShelterService {

    private final PetShelterRepository petShelterRepository;

    public void createPetShelter(PetShelter petShelterToCreate) {
        if (!petShelterToCreate.getRole().equals(Role.PET_SHELTER)) {
            throw new IllegalArgumentException("Currently registered user is not pet shelter");
        }

        petShelterRepository.saveAndFlush(petShelterToCreate);
    }
}
