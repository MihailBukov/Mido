package com.mido.mappers;

import com.mido.dtos.AdvertisementDto;
import com.mido.models.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {
    @Mapping(source = "", target = "")
    Advertisement mapFromDto(AdvertisementDto ad);

    @Mapping(source = "", target = "")
    AdvertisementDto mapToDto(Advertisement ad);
}
