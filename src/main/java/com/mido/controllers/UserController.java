package com.mido.controllers;

import com.mido.dtos.UserDto;
import com.mido.mappers.UserMapper;
import com.mido.models.User;
import com.mido.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> searchForUser(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String username) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        User userWanted = userService.loadUserByUsername(username);
        UserDto userDto = userMapper.userToUserDto(userWanted);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
