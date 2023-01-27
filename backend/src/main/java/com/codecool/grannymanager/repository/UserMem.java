package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMem {

    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return List.copyOf(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.isId(id)).findFirst().orElseThrow();
    }

    public int getUserId(String email) {
        return users.stream().filter(user -> user.isEmail(user.getEmail())).findFirst().orElseThrow().getId();
    }

    public void updateEmail(User user, String email) {
        user.setEmail(email);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(password);
    }
}
