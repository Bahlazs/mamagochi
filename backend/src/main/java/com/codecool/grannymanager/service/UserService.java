package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.requestmodel.LoginRequest;
import com.codecool.grannymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public User login(LoginRequest request){
        User user = userRepository.findUserByEmail(request.getEmail());
        if(user.getPassword().equals(request.getPassword())){
            return user;
        }
        return null;
    }

    public void updateUser(User updatedUser){
        User userToUpdate = userRepository.findUserById(updatedUser.getId());

        if(updatedUser.getEmail() != null){
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getPassword() != null){
            userToUpdate.setPassword(updatedUser.getPassword());
        }
        if(updatedUser.getName() != null){
            userToUpdate.setName(updatedUser.getName());
        }
        if(updatedUser.getGranny() != null){
            userToUpdate.setGranny(updatedUser.getGranny());
        }
        userRepository.save(userToUpdate);
    }


}
