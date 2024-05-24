package com.mido.repositories;

import com.mido.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void addUser(User user) {
    }

    public void editUser(User user) {

    }

    public void deleteUser(Integer id) {

    }

    public User findById(Integer id){
        return new User(); //should be changed
    }
}
