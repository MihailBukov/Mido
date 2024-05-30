package com.mido.services;

import com.mido.models.User;
import com.mido.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        userRepo.save(user);
        return user;
    }

    public User findById(Integer id) {
        return userRepo.getReferenceById(id);
    }

    public User updateUser(User user) {
        userRepo.save(user);
        return user;
    }

    public void removeUser(String username) {
        userRepo.delete(username);
    }

    public List<User> searchUsers(String role, String username) {
        return userRepo.searchUsers(role, username);
    }

    public User findByName(String username) {
        return userRepo.findByUsername(username);
    }
}
