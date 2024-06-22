package com.mido.mappers;

import com.mido.dtos.requests.FirstStepRegister;
import com.mido.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User firstStepRegisterToUser(FirstStepRegister obj);
}
