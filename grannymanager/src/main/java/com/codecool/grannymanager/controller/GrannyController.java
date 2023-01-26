package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.requestmodel.GrannyCreateRequest;
import com.codecool.grannymanager.model.requestmodel.GrannyGetRequest;
import com.codecool.grannymanager.service.GrannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;

    @Autowired
    public GrannyController(GrannyService grannyService) {
        this.grannyService = grannyService;
    }

    @PostMapping("/create-granny")
    public void createGranny(@RequestBody GrannyCreateRequest request) {
        int userId = request.getUserId();
        String name = request.getName();
        grannyService.createGranny(userId, name);
    }

    @GetMapping("/visit-granny/{id}")
    public ResponseEntity<Granny> visitGranny(@PathVariable int id) {
        Granny granny = grannyService.visitGranny(id);
        return ResponseEntity.ok().body(granny);
    }

//    TODO: should change these to PUT/PATCH requests
//    TODO: review -> changed from RequestBody to PathVariable - frontend couldn't send a response body with get request,
//    TODO: the backend should retrieve the id from session

    @GetMapping("/feed-pie/{id}")
    public ResponseEntity<Integer> feedPie(@PathVariable int id) {
        Granny granny = grannyService.feedPie(id);
        return ResponseEntity.ok().body(granny.getHealth().getStat());
    }

    @GetMapping("/play-mahjong/{id}")
    public ResponseEntity<Integer> playMahjong(@PathVariable int id) {
        Granny granny = grannyService.playMahjong(id);
        System.out.println(ResponseEntity.ok().body(granny.getMood().getStat()));
        return ResponseEntity.ok().body(granny.getMood().getStat());
    }

    @GetMapping("/clean-house/{id}")
    public ResponseEntity<Integer> cleanHouse(@PathVariable int id) {
        Granny granny = grannyService.cleanHouse(id);
        return ResponseEntity.ok().body(granny.getEnvironment().getStat());
    }


    @GetMapping("/spend-one-day")
    public void spendOneDay(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneDay(request.getUserId());
    }

    @GetMapping("/spend-one-week")
    public void spendOneWeek(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneWeek(request.getUserId());
    }

}
