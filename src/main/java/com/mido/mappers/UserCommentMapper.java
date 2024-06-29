package com.mido.mappers;

import com.mido.dtos.UserCommentDto;
import com.mido.models.UserComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCommentMapper {
    @Mapping(source = "writtenTo.username", target = "writtenTo")
    @Mapping(source = "writtenBy.username", target = "writtenBy")
    @Mapping(target = "writtenByName", expression = "java(comment.getWrittenBy().getNameOfUser())")
    @Mapping(target = "writtenToName", expression = "java(comment.getWrittenTo().getNameOfUser())")
    UserCommentDto commentToCommentDto(UserComment comment);
}
