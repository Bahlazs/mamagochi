package com.codecool.grannymanager.controller;



import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.model.enumgrannyproperties.Environment;
import com.codecool.grannymanager.model.enumgrannyproperties.Health;
import com.codecool.grannymanager.model.enumgrannyproperties.Mood;
import com.codecool.grannymanager.model.requestmodel.GrannyCreateRequest;
import com.codecool.grannymanager.security.JwtService;
import com.codecool.grannymanager.service.GrannyService;
import com.codecool.grannymanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;
    private final JwtService jwtService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<Void> createGranny(@RequestBody GrannyCreateRequest request,
                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        ResponseEntity<Void> response;
        String userName = jwtService.getUserNameFromHeader(header);
        if (request.getName() != null) {
            Optional<User> optionalUser = userService.getUserByName(userName);
            User user = optionalUser.orElse(null);
            if (user != null) {
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

    private void registerGrannyForUser(User user, String nameOfGranny) {
        Granny granny = new Granny(user, nameOfGranny);
        grannyService.registerGranny(granny);
        user.setGranny(granny);
    }

    @GetMapping
    public ResponseEntity<Granny> visitGranny(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        ResponseEntity<Granny> response;
        String userName = jwtService.getUserNameFromHeader(header);
        Optional<User> optionalUser = userService.getUserByName(userName);
        User user = optionalUser.orElse(null);
        if (user != null) {
            grannyService.visitGranny(user.getGranny());
            if (user.getGranny() != null) {
                response = ResponseEntity.ok().body(user.getGranny());
            } else {
                response = ResponseEntity.status(400).body(null);
            }
        } else {
            response = ResponseEntity.status(400).body(null);
        }

        return response;
    }

    @GetMapping("/feed-granny")
    public ResponseEntity<Health> feedGranny(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        ResponseEntity<Health> response;
        String userName = jwtService.getUserNameFromHeader(header);
        Optional<User> optionalUser = userService.getUserByName(userName);
        User user = optionalUser.orElse(null);
        if (user != null) {
            grannyService.feedGranny(user.getGranny());
            response = ResponseEntity.ok().body(user.getGranny().getHealthStat());
        } else {
            response = ResponseEntity.status(400).body(null);
        }
        return response;
    }

    @GetMapping("/clean-house")
    public ResponseEntity<Environment> cleanGrannyHouse(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        ResponseEntity<Environment> response;
        String userName = jwtService.getUserNameFromHeader(header);
        Optional<User> optionalUser = userService.getUserByName(userName);
        User user = optionalUser.orElse(null);
        if (user != null) {
            grannyService.cleanHouse(user.getGranny());
            response = ResponseEntity.ok().body(user.getGranny().getEnvironmentStat());
        } else {
            response = ResponseEntity.status(400).body(null);
        }
        return response;
    }

    @GetMapping("/play-mahjong")
    public ResponseEntity<Mood> playMahjong(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        ResponseEntity<Mood> response;
        String userName = jwtService.getUserNameFromHeader(header);
        Optional<User> optionalUser = userService.getUserByName(userName);
        User user = optionalUser.orElse(null);
        if (user != null) {
            grannyService.playMahjongWithGranny(user.getGranny());
            response = ResponseEntity.ok().body(user.getGranny().getMoodStat());
        } else {
            response = ResponseEntity.status(400).body(null);
        }
        return response;
    }


   /* @GetMapping("/spend-one-day")
    public void spendOneDay() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.jumpOneDay(user.getGranny());
    }

    @GetMapping("/spend-one-week")
    public void spendOneWeek() {
        User user = userService.getUserById(sessionService.get("userId"));
        grannyService.jumpOneWeek(user.getGranny());
    }
*/

}
