package com.mido.services;

import com.mido.dtos.ClientDto;
import com.mido.dtos.PetShelterDto;
import com.mido.mappers.ClientMapper;
import com.mido.models.Client;
import com.mido.models.PetShelter;
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

    public ClientDto getClientByUsername(String username) {
        return clientMapper.clientToClientDto(
                clientRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Client not found"))
        );
    }

    public ClientDto updateClient(String username, ClientDto newClientDto) {
        if (newClientDto.age() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        Client oldClient =
                clientRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Client with this username does not exist"));
        Client newClient = clientMapper.clientDtoToClient(newClientDto);
        newClient.setId(oldClient.getId());
        newClient.setUsername(oldClient.getUsername());
        newClient.setEmail(oldClient.getEmail());
        newClient.setPassword(oldClient.getPassword());
        newClient.setRole(oldClient.getRole());
        newClient.setStatus(oldClient.getStatus());
        return clientMapper.clientToClientDto(clientRepository.saveAndFlush(newClient));
    }
}
