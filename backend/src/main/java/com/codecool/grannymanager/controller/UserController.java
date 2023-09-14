package com.codecool.grannymanager.controller;

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



    @GetMapping("/logout")
    public void logout(){
       // todo
    }

}
