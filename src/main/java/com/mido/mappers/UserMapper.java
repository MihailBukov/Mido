package com.mido.mappers;

import com.mido.dtos.UserDto;
import com.mido.dtos.requests.RegisterRequest;
import com.mido.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User obj);
}
