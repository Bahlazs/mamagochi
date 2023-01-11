package com.codecool.grannymanager.controller;

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

    @GetMapping("/visit-granny{id}")
    public ResponseEntity<Void> visitGranny(@PathVariable int id) {
        grannyService.visitGranny(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed-pie{id}")
    public ResponseEntity<Void> feedPie(@PathVariable int id) {
        grannyService.feedPie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/play-mahjong{id}")
    public ResponseEntity<Void> playMahjong(@PathVariable int id) {
        grannyService.playMahjong(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clean-house{id}")
    public ResponseEntity<Void> cleanHouse(@PathVariable int id) {
        grannyService.cleanHouse(id);
        return ResponseEntity.ok().build();
    }

}
