package com.mido.mappers;

import com.mido.dtos.UserDto;
import com.mido.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "", target = "")
    User mapFromDto(UserDto user);

    @Mapping(source = "", target = "")
    UserDto mapToDto(User user);
}
