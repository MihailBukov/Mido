package com.mido.services;

import com.mido.models.User;
import com.mido.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        userRepo.addUser(user);
        return user;
    }

    public User findById(Integer id) {
        return userRepo.findById(id);
    }

    public User updateUser(User user) {
        userRepo.editUser(user);
        return user;
    }

    public void removeUser(Integer id) {
        userRepo.deleteUser(id);
    }
}
