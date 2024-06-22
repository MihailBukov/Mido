package com.mido.mappers;

import com.mido.dtos.requests.PetShelterRegisterRequest;
import com.mido.models.PetShelter;
import com.mido.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PetShelterMapper {
    PetShelter petShelterRegisterRequestToPetShelter(PetShelterRegisterRequest obj);

    @Mapping(target = "authorities", ignore = true)
    void updatePetShelterFromUser(User source, @MappingTarget PetShelter destination);
}
