package com.mido.dtos.requests;

import com.mido.models.Role;

public record FirstStepRegister(String username, String password, String email, Role role) { }
