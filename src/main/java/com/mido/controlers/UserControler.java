package com.mido.controlers;

import com.mido.dtos.UserDto;
import com.mido.dtos.UserDto;
import com.mido.mappers.UserMapper;
import com.mido.models.User;
import com.mido.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserControler {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/user")
    public ResponseEntity<List<UserDto>> searchForUser(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String username) {

        List<User> users = userService.searchUsers(role, username);

        List<UserDto> userDtos = users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/api/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        User user = userService.findByName(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = userMapper.mapToDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/api/User/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        User user = userMapper.mapFromDto(userDto);
        user.setUsername(username);//not sure about this line
        return new ResponseEntity<>(userMapper.mapToDto(userService.updateUser(user)), HttpStatus.OK);
    }

    @PostMapping("/api/User")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapFromDto(userDto);
        return new ResponseEntity<>(userMapper.mapToDto(userService.createUser(user)), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) {
        userService.removeUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
