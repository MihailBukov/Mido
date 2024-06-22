package com.mido.services;

import com.mido.models.Client;
import com.mido.models.Role;
import com.mido.models.User;
import com.mido.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    //private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NoSuchElementException(String.format("User with username: %s was not found", username)));
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NoSuchElementException(String.format("User with email: %s was not found", email)));
    }

    public void createUser(User userToCreate) {
        String username = userToCreate.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException(String.format("User with username: %s already exists in the database", username));
        }

        String email = userToCreate.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException(String.format("User with email: %s already exists in the database", email));
        }

        if (userToCreate.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Unauthorized to create admin account");
        }

        //userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        userRepository.saveAndFlush(userToCreate);
    }
}
