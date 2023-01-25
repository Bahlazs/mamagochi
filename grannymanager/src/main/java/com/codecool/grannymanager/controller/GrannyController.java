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

    @GetMapping("/visit-granny")
    public ResponseEntity<Granny> visitGranny(@RequestBody GrannyGetRequest request) {
        Granny granny = grannyService.visitGranny(request.getUserId());
        return ResponseEntity.ok().body(granny);
    }

    @GetMapping("/feed-pie")
    public void feedPie(@RequestBody GrannyGetRequest request) {
        grannyService.feedPie(request.getUserId());
    }

    @GetMapping("/play-mahjong")
    public void playMahjong(@RequestBody GrannyGetRequest request) {
        grannyService.playMahjong(request.getUserId());
    }

    @GetMapping("/clean-house")
    public void cleanHouse(@RequestBody GrannyGetRequest request) {
        grannyService.cleanHouse(request.getUserId());
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
