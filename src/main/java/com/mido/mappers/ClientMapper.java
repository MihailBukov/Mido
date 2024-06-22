package com.mido.mappers;

import com.mido.dtos.requests.ClientRegisterRequest;
import com.mido.models.Client;
import com.mido.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client ClientRegisterRequestToClient(ClientRegisterRequest obj);

    @Mapping(target = "authorities", ignore = true)
    void updateClientFromUser(User source, @MappingTarget Client destination);
}
