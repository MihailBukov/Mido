package com.mido.dtos;

import com.mido.models.Role;
import com.mido.models.Status;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
    private Status status;
}
