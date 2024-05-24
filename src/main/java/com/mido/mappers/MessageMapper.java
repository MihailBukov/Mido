package com.mido.mappers;

import com.mido.dtos.MessageDto;
import com.mido.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "", target = "")
    Message mapFromDto(MessageDto messageDto);

    @Mapping(source = "", target = "")
    MessageDto mapToDto(Message message);
}
