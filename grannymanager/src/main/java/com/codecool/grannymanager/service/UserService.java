package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.repository.UserMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserMem userMem;

    @Autowired
    public UserService(UserMem userMem) {
        this.userMem = userMem;
    }

    public void registerUser(User user) {
        userMem.addUser(user);
    }

    public void deleteUser(int id) {
        User user = userMem.getUserById(id);
        userMem.deleteUser(user);
    }

    public void updateUser(int id, String email, String password) {
        User user = userMem.getUserById(id);
        userMem.updateEmail(user, email);
        userMem.updatePassword(user, password);
    }
}
