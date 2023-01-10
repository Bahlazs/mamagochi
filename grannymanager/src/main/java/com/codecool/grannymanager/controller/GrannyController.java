package com.codecool.grannymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrannyController {
private final GrannyService grannyService;
    @Autowired
    public GrannyController(GrannyService grannyService) {
        this.grannyService = grannyService;
    }
}
