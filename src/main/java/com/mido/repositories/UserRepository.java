package com.mido.repositories;

import com.mido.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public void addUser(User user) {
    }

    public void editUser(User user) {

    }

    public void deleteUser(String username) {

    }

    public User findById(Integer id){
        return new User(); //should be changed
    }

    public List<User> searchUsers(String role, String username) {
        return null;
    }

    public User findByUsername(String username) {
        return null;
    }
}
