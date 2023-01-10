package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.repository.GrannyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrannyService {
    private final GrannyRepository grannyRepository;

    @Autowired
    public GrannyService(GrannyRepository grannyRepository) {
        this.grannyRepository = grannyRepository;
    }

    public void visitGranny(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.checkOnGranny();
    }

    public void feedPie(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.increaseHealth();
    }

    public void playMahjong(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.increaseFeeling();
    }

    public void cleanHouse(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.increaseEnvironment();
    }

}
