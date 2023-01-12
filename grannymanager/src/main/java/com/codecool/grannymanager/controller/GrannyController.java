package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.grannyproperties.Stat;
import com.codecool.grannymanager.model.requestmodel.GrannyCreateRequest;
import com.codecool.grannymanager.model.requestmodel.GrannyGetRequest;
import com.codecool.grannymanager.service.GrannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codecool.grannymanager.service.GrannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;

    @Autowired
    public GrannyController(GrannyService grannyService) {
        this.grannyService = grannyService;
    }

    @PostMapping("/create-granny")
    public ResponseEntity<String> createGranny(@RequestBody GrannyCreateRequest request) {
        int userId = request.getUserId();
        String name = request.getName();
        grannyService.createGranny(userId, name);
        return ResponseEntity.ok().body("Granny created with user id: " + userId);
    }

    @GetMapping("/visit-granny")
    public ResponseEntity<Granny> visitGranny(@RequestBody GrannyGetRequest request) {
        Granny granny = grannyService.visitGranny(request.getUserId());
        return ResponseEntity.ok().body(granny);
    }

    @GetMapping("/feed-pie")
    public ResponseEntity<String> feedPie(@RequestBody GrannyGetRequest request) {
        String stringValueOfOriginalStat = getStringValueOfGrannyOriginalHealthStat(request);
        Granny granny = grannyService.feedPie(request.getUserId());
        String stringValueOfNewStat = getStringValueOfGrannyNewHealthStat(granny);
        return ResponseEntity.ok().body("Granny Health changed from \"" + stringValueOfOriginalStat + "\" to \"" + stringValueOfNewStat + "\"");
    }
    //Presentation purpose only
    private String getStringValueOfGrannyOriginalHealthStat(GrannyGetRequest request) {
        Granny granny = grannyService.getGranny(request.getUserId());
        Stat grannyOriginalStat = granny.getHealth();
        return grannyOriginalStat.getStringValueOfEnum();
    }
    //Presentation purpose only
    private String getStringValueOfGrannyNewHealthStat(Granny granny) {
        Stat grannyNewStat = granny.getHealth();
        return grannyNewStat.getStringValueOfEnum();
    }

    @GetMapping("/play-mahjong")
    public ResponseEntity<String> playMahjong(@RequestBody GrannyGetRequest request) {
        String stringValueOfOriginalStat = getStringValueOfGrannyOriginalMoodStat(request);
        Granny granny = grannyService.playMahjong(request.getUserId());
        String stringValueOfNewStat = getStringValueOfGrannyNewMoodStat(granny);
        return ResponseEntity.ok().body("Granny Mood changed from \"" + stringValueOfOriginalStat + "\" to \"" + stringValueOfNewStat + "\"");
    }
    //Presentation purpose only
    private String getStringValueOfGrannyOriginalMoodStat(GrannyGetRequest request) {
        Granny granny = grannyService.getGranny(request.getUserId());
        Stat grannyOriginalStat = granny.getMood();
        return grannyOriginalStat.getStringValueOfEnum();
    }
    //Presentation purpose only
    private String getStringValueOfGrannyNewMoodStat(Granny granny) {
        Stat grannyNewStat = granny.getMood();
        return grannyNewStat.getStringValueOfEnum();
    }

    @GetMapping("/clean-house")
    public ResponseEntity<String> cleanHouse(@RequestBody GrannyGetRequest request) {
        String stringValueOfOriginalStat = getStringValueOfGrannyOriginalEnvironmentStat(request);
        Granny granny = grannyService.cleanHouse(request.getUserId());
        String stringValueOfNewStat = getStringValueOfGrannyNewEnvironmentStat(granny);
        return ResponseEntity.ok().body("Granny Environment changed from \"" + stringValueOfOriginalStat + "\" to \"" + stringValueOfNewStat + "\"");
    }
    //Presentation purpose only
    private String getStringValueOfGrannyOriginalEnvironmentStat(GrannyGetRequest request) {
        Granny granny = grannyService.getGranny(request.getUserId());
        Stat grannyOriginalStat = granny.getEnvironment();
        return grannyOriginalStat.getStringValueOfEnum();
    }
    //Presentation purpose only
    private String getStringValueOfGrannyNewEnvironmentStat(Granny granny) {
        Stat grannyNewStat = granny.getEnvironment();
        return grannyNewStat.getStringValueOfEnum();
    }

    @GetMapping("/spend-one-day")
    public ResponseEntity<String> spendOneDay(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneDay(request.getUserId());
        return ResponseEntity.ok().body("One day spent! You should check on Granny");
    }

    @GetMapping("/spend-one-week")
    public ResponseEntity<String> spendOneWeek(@RequestBody GrannyGetRequest request) {
        grannyService.jumpOneWeek(request.getUserId());
        return ResponseEntity.ok().body("One week spent! You really should check on Granny");
    }

}
