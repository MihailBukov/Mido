package com.mido.services;

import com.mido.models.Client;
import com.mido.models.Role;
import com.mido.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void createClient(Client clientToCreate) {
        if (!clientToCreate.getRole().equals(Role.CLIENT)) {
            throw new IllegalArgumentException("Currently registered user is not client");
        }

        clientRepository.saveAndFlush(clientToCreate);
    }
}
