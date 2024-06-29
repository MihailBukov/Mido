package com.mido.services;

import com.mido.dtos.ClientDto;
import com.mido.mappers.ClientMapper;
import com.mido.models.Client;
import com.mido.models.Role;
import com.mido.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public void createClient(Client clientToCreate) {
        if (!clientToCreate.getRole().equals(Role.CLIENT)) {
            throw new IllegalArgumentException("Currently registered user is not client");
        }

        clientRepository.saveAndFlush(clientToCreate);
    }

    public ClientDto getClientById(Long id) {
        return clientMapper.clientToClientDto(
                clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"))
        );
    }
}
