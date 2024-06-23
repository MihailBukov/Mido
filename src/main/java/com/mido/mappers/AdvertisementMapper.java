package com.mido.mappers;

import com.mido.dtos.AdvertisementDto;
import com.mido.dtos.requests.AdvertisementRequest;
import com.mido.models.Advertisement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {
    Advertisement advertisementCreateRequestToAdvertisement(AdvertisementRequest obj);

    AdvertisementDto adToAdDto(Advertisement obj);
}
