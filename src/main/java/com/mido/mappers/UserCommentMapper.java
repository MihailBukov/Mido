package com.mido.mappers;

import com.mido.dtos.UserCommentDto;
import com.mido.models.UserComment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCommentMapper {
    UserCommentDto commentToCommentDto(UserComment rating);
}
