package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.UserLogin;
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

    public User getUserById(int id) {
        return users.stream().filter(user -> user.isId(id)).findFirst().orElseThrow();
    }


    //throws NoSuchElement if email is incorrect
    public int getUserId(UserLogin userLogin) {
        User thisUser = users.stream().filter(user -> user.isEmail(user.getEmail())).findFirst().orElseThrow();
        if (thisUser.getPassword().equals(userLogin.getPassword())) {
            return thisUser.getId();
        }
        //Password is incorrect
        return -1;
    }

    public void updateEmail(User user, String email) {
        user.setEmail(email);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(password);
    }
}
