package com.codecool.grannymanager.controller;

import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.GrannyCreateRequest;
import com.codecool.grannymanager.service.GrannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/granny")
public class GrannyController {
    private final GrannyService grannyService;

    @Autowired
    public GrannyController(GrannyService grannyService) {
        this.grannyService = grannyService;
    }

    @PostMapping("/create-granny")
    public ResponseEntity<Integer> createGranny(@RequestBody GrannyCreateRequest request) {
        int userId = request.getUserId();
        String name = request.getName();
        grannyService.createGranny(userId, name);
        return ResponseEntity.ok().body(userId);
    }

    @GetMapping("/visit-granny{id}")
    public ResponseEntity<Granny> visitGranny(@PathVariable int id) {
        Granny granny = grannyService.visitGranny(id);
        return ResponseEntity.ok().body(granny);
    }

    @GetMapping("/feed-pie{id}")
    public ResponseEntity<Granny> feedPie(@PathVariable int id) {
        Granny granny = grannyService.feedPie(id);
        return ResponseEntity.ok().body(granny);
    }

    @GetMapping("/play-mahjong{id}")
    public ResponseEntity<Granny> playMahjong(@PathVariable int id) {
        Granny granny = grannyService.playMahjong(id);
        return ResponseEntity.ok().body(granny);
    }

    @GetMapping("/clean-house{id}")
    public ResponseEntity<Granny> cleanHouse(@PathVariable int id) {
        Granny granny = grannyService.cleanHouse(id);
        return ResponseEntity.ok().body(granny);
    }

}
