package com.codecool.grannymanager.controller;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    @ResponseBody
    public void register(@RequestBody User user) {
        userService.registerUser(user);
    }
}
