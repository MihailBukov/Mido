package com.mido.services;

import com.mido.models.User;
import com.mido.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public User createUser(User user) {
        userRepo.save(user);
        return user;
    }

    public User findById(Integer id) {
        //return userRepo.getReferenceById(id);
        return null;
    }

    public User updateUser(User user) {
        userRepo.save(user);
        return user;
    }

    public void removeUser(String username) {
        //userRepo.delete(username);
    }

    public List<User> searchUsers(String role, String username) {
        return null;
    }

    public User findByName(String username) {
        return null;
    }
}
