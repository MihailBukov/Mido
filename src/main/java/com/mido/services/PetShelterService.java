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

    public PetShelterDto getPetShelterByUsername(String username) {
        return petShelterMapper.petShelterToPetShelterDto(petShelterRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException("Pet shelter not found")
        ));
    }

    public PetShelterDto updatePetShelter(String username, PetShelterDto newPetShelterDto) {
        if (newPetShelterDto.capacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        PetShelter oldPetShelter =
                petShelterRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Pet shelter with this username does not exist"));
        PetShelter newPetShelter = petShelterMapper.petShelterDtoToPetShelter(newPetShelterDto);
        newPetShelter.setId(oldPetShelter.getId());
        newPetShelter.setUsername(oldPetShelter.getUsername());
        newPetShelter.setEmail(oldPetShelter.getEmail());
        newPetShelter.setPassword(oldPetShelter.getPassword());
        newPetShelter.setRole(oldPetShelter.getRole());
        newPetShelter.setStatus(oldPetShelter.getStatus());
        return petShelterMapper.petShelterToPetShelterDto(petShelterRepository.saveAndFlush(newPetShelter));
    }
}
