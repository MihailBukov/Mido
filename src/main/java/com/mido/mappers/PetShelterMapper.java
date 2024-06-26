package com.mido.mappers;

import com.mido.dtos.PetShelterDto;
import com.mido.dtos.requests.RegisterRequest;
import com.mido.models.PetShelter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetShelterMapper {
    PetShelter registerRequestToPetShelter(RegisterRequest obj);

    PetShelterDto petShelterToPetShelterDto(PetShelter obj);

    PetShelter petShelterDtoToPetShelter(PetShelterDto obj);
}
