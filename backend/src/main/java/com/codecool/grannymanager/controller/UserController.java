package com.codecool.grannymanager.controller;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.requestmodel.LoginRequest;
import com.codecool.grannymanager.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public void register(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@RequestBody LoginRequest request){
        User user = userService.login(request);


    }

    @GetMapping("/me")
    public void checkMe(){

    }
}
