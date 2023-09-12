package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.enumgrannyproperties.Environment;
import com.codecool.grannymanager.model.enumgrannyproperties.Health;
import com.codecool.grannymanager.model.enumgrannyproperties.Mood;
import com.codecool.grannymanager.model.requestmodel.GrannyCreateRequest;
import com.codecool.grannymanager.model.requestmodel.GrannyGetRequest;
import com.codecool.grannymanager.repository.UserRepository;
import com.codecool.grannymanager.service.GrannyService;
import com.codecool.grannymanager.service.SessionService;
import com.codecool.grannymanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Long.parseLong;

@RestController
@AllArgsConstructor
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;

    private final SessionService sessionService;

    private final UserService userService;
    private final UserRepository userRepository;



    @PostMapping
    public ResponseEntity<Void> createGranny(@RequestBody GrannyCreateRequest request) {
        ResponseEntity<Void> response;

        if(request.getName() != null){
            User user = userService.getUserById(parseLong("1"));
            if(user != null){
                registerGrannyForUser(user, request.getName());
                userService.updateUser(user);
                response = ResponseEntity.ok().build();
            } else {
                response = ResponseEntity.status(400).build();
            }
        } else {
            response = ResponseEntity.status(400).build();
        }

       return response;
    }

    private void registerGrannyForUser(User user, String nameOfGranny){
        Granny granny = new Granny(user, nameOfGranny);
        grannyService.registerGranny(granny);
        user.setGranny(granny);
    }

    @GetMapping
    public ResponseEntity<Granny> visitGranny() {
        ResponseEntity<Granny> response;
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.visitGranny(user.getGranny());

        if(user.getGranny() != null){
            response = ResponseEntity.ok().body(user.getGranny());
        }else {
            response = ResponseEntity.status(400).body(null);
        }

        return response;
    }

    @GetMapping ("/feed-granny")
    public ResponseEntity<Health> feedGranny() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.feedGranny(user.getGranny());
        return ResponseEntity.ok().body(user.getGranny().getHealthStat());
    }

    @GetMapping ("/clean-house")
    public ResponseEntity<Environment> cleanGrannyHouse() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.cleanHouse(user.getGranny());
        return ResponseEntity.ok().body(user.getGranny().getEnvironmentStat());
    }

    @GetMapping ("/play-mahjong")
    public ResponseEntity<Mood> playMahjong() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.playMahjongWithGranny(user.getGranny());
        return ResponseEntity.ok().body(user.getGranny().getMoodStat());
    }


    @GetMapping("/spend-one-day")
    public void spendOneDay() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.jumpOneDay(user.getGranny());
    }

    @GetMapping("/spend-one-week")
    public void spendOneWeek() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.jumpOneWeek(user.getGranny());
    }

}
