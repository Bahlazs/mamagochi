package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.User;

import com.codecool.grannymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Void> registerUser(User user) {
        try{
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }catch (Error e){
            return ResponseEntity.status(400).build();
        }

    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }



    public void updateUser(User updatedUser){
        User userToUpdate = userRepository.findUserById(updatedUser.getId());

        if(updatedUser.getEmail() != null){
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getPassword() != null){
            userToUpdate.setPassword(updatedUser.getPassword());
        }
        if(updatedUser.getUsername() != null){
            userToUpdate.setUserName(updatedUser.getUsername());
        }
        if(updatedUser.getGranny() != null){
            userToUpdate.setGranny(updatedUser.getGranny());
        }
        userRepository.save(userToUpdate);
    }


}
