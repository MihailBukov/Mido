package com.mido.repositories;


import com.mido.models.Client;
import com.mido.models.PetShelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}
