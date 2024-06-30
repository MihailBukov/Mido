package com.mido.mappers;

import com.mido.dtos.ClientDto;
import com.mido.dtos.requests.RegisterRequest;
import com.mido.models.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client registerRequestToClient(RegisterRequest obj);

    ClientDto clientToClientDto(Client obj);

    Client clientDtoToClient(ClientDto obj);
}
