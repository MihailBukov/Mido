package com.mido.repositories;

import com.mido.models.PetShelter;
import com.mido.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetShelterRepository extends JpaRepository<PetShelter, Long> {
    Optional<PetShelter> findByUsername(String username);
}
