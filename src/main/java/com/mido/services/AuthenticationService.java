package com.mido.services;

import com.mido.dtos.requests.AuthenticationRequest;
import com.mido.dtos.requests.ClientRegisterRequest;
import com.mido.dtos.requests.FirstStepRegister;
import com.mido.dtos.requests.PetShelterRegisterRequest;
import com.mido.mappers.ClientMapper;
import com.mido.mappers.PetShelterMapper;
import com.mido.mappers.UserMapper;
import com.mido.models.Client;
import com.mido.models.PetShelter;
import com.mido.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    private final JwtService jwtService;

    private final ClientService clientService;

    private final PetShelterService petShelterService;

    private final UserMapper userMapper;

    private final PetShelterMapper petShelterMapper;

    private final ClientMapper clientMapper;

    private final AuthenticationManager authenticationManager;

    public void firstStepRegister(FirstStepRegister request) {
        User userToCreate = userMapper.firstStepRegisterToUser(request);
        userService.createUser(userToCreate);
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

    public void registerClient(ClientRegisterRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        Client client = clientMapper.ClientRegisterRequestToClient(request);
        clientMapper.updateClientFromUser(user, client);
        clientService.createClient(client);
    }

    public void registerPetShelter(PetShelterRegisterRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        PetShelter petShelter = petShelterMapper.petShelterRegisterRequestToPetShelter(request);
        petShelterMapper.updatePetShelterFromUser(user, petShelter);
        petShelterService.createPetShelter(petShelter);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());

        return extraClaims;
    }
}
