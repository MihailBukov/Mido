package com.mido.services;

import com.mido.dtos.requests.AuthenticationRequest;
import com.mido.dtos.requests.RegisterRequest;
import com.mido.mappers.ClientMapper;
import com.mido.mappers.PetShelterMapper;
import com.mido.models.Client;
import com.mido.models.PetShelter;
import com.mido.models.Role;
import com.mido.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    private final JwtService jwtService;

    private final ClientService clientService;

    private final PetShelterService petShelterService;

    private final PetShelterMapper petShelterMapper;

    private final ClientMapper clientMapper;

    private final AuthenticationManager authenticationManager;

    private final FileStorageService fileStorageService;

    public void registerUser(RegisterRequest request, MultipartFile picture) throws IOException {
        String photoFilePath = fileStorageService.storeFile(picture);
        if (Role.CLIENT.equals(request.role())) {
            validateClientRegisterParams(request);
            Client client = clientMapper.registerRequestToClient(request);
            client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
            client.setPhotoFilePath(photoFilePath);
            clientService.createClient(client);
        } else if (Role.PET_SHELTER.equals(request.role())) {
            validatePetShelterRegisterParams(request);
            PetShelter petShelter = petShelterMapper.registerRequestToPetShelter(request);
            petShelter.setPassword(new BCryptPasswordEncoder().encode(petShelter.getPassword()));
            petShelter.setPhotoFilePath(photoFilePath);
            petShelterService.createPetShelter(petShelter);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    private void validateClientRegisterParams(RegisterRequest request) {
        if (request.age() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        if (!isAlpha(request.firstName()) && !isAlpha(request.middleName()) && !isAlpha(request.lastName())) {
            throw new IllegalArgumentException("First, middle and last names must all be alphanumeric");
        }
    }

    private void validatePetShelterRegisterParams(RegisterRequest request) {
        if (request.capacity() < 0) {
            throw new IllegalArgumentException("Capacity should be greater than 0");
        }
        if (!isAlpha(request.name())) {
            throw new IllegalArgumentException("Shelter name must all be alphanumeric");
        }
    }

    public String authenticate(AuthenticationRequest request) {
        String username = request.username();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    username,
                    request.password()
            )
        );

        User user = userService.loadUserByUsername(username);
        return jwtService.generateToken(generateExtraClaims(user), user);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        extraClaims.put("id", user.getId());

        return extraClaims;
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
