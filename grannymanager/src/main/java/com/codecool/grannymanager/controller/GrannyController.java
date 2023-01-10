package com.codecool.grannymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

}
