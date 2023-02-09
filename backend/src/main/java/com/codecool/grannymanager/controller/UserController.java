package com.codecool.grannymanager.controller;

import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.requestmodel.LoginRequest;
import com.codecool.grannymanager.service.SessionService;
import com.codecool.grannymanager.service.UserService;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public UserController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public void register(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@RequestBody LoginRequest loginRequest, Model model){
        User user = userService.login(loginRequest);
        if(user != null){
            sessionService.put("userId", user.getId());
            if(user.getGranny() != null){sessionService.put("grannyId",user.getGranny().getId());}
        }

    }

    @GetMapping("/logout")
    public void logout(){
        sessionService.logout();
    }

}
