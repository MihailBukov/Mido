package com.mido.services;

import com.mido.dtos.PetShelterDto;
import com.mido.mappers.PetShelterMapper;
import com.mido.models.PetShelter;
import com.mido.models.Role;
import com.mido.repositories.PetShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PetShelterService {

    private final PetShelterRepository petShelterRepository;

    private final PetShelterMapper petShelterMapper;

    public void createPetShelter(PetShelter petShelterToCreate) {
        if (!petShelterToCreate.getRole().equals(Role.PET_SHELTER)) {
            throw new IllegalArgumentException("Currently registered user is not pet shelter");
        }

        petShelterRepository.saveAndFlush(petShelterToCreate);
    }

    public PetShelterDto getPetShelterById(Long id) {
        return petShelterMapper.petShelterToPetShelterDto(petShelterRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Pet shelter not found")
        ));
    }
}
