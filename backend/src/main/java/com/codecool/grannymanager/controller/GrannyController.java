package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.requestmodel.GrannyCreateRequest;
import com.codecool.grannymanager.model.requestmodel.GrannyGetRequest;
import com.codecool.grannymanager.repository.UserRepository;
import com.codecool.grannymanager.service.GrannyService;
import com.codecool.grannymanager.service.SessionService;
import com.codecool.grannymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;

    private final SessionService sessionService;

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public GrannyController(GrannyService grannyService, SessionService sessionService, UserService userService,
                            UserRepository userRepository) {
        this.grannyService = grannyService;
        this.sessionService = sessionService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create-granny")
    public void createGranny(@RequestBody GrannyCreateRequest request) {
        User user = userService.getUserById(sessionService.get("userId"));

        registerGrannyForUser(user, request.getName());
        userService.updateUser(user);
    }

    private void registerGrannyForUser(User user, String nameOfGranny){
        Granny granny = new Granny(user, nameOfGranny);
        grannyService.registerGranny(granny);
        user.setGranny(granny);
    }

    @GetMapping("/visit-granny/{id}")
    public Granny visitGranny(@PathVariable long id) {
        return grannyService.visitGranny(id);
    }

//    TODO: should change these to PUT/PATCH requests
//    TODO: review -> changed from RequestBody to PathVariable - frontend couldn't send a response body with get request,
//    TODO: the backend should retrieve the id from session



    @GetMapping("/spend-one-day")
    public void spendOneDay(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneDay(request.getUserId());
    }

    @GetMapping("/spend-one-week")
    public void spendOneWeek(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneWeek(request.getUserId());
    }

}
