package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.grannyproperties.Environment;
import com.codecool.grannymanager.model.grannyproperties.Health;
import com.codecool.grannymanager.model.grannyproperties.Mood;
import com.codecool.grannymanager.model.grannyproperties.Stat;
import com.codecool.grannymanager.repository.GrannyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class GrannyService {
    private final GrannyRepository grannyRepository;

    @Autowired
    public GrannyService(GrannyRepository grannyRepository) {
        this.grannyRepository = grannyRepository;
    }

    public void visitGranny(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        LocalDateTime lastVisit = granny.getLastVisit();
        int hoursSinceLastVisit = (int)ChronoUnit.HOURS.between(lastVisit, LocalDateTime.now());
        int daysSinceLastVisit = Math.floorDiv(hoursSinceLastVisit, 24);
        checkOnGranny(granny, daysSinceLastVisit);
        granny.setLastVisit(LocalDateTime.now());
    }

    private void checkOnGranny(Granny granny, int daysSinceLastVisit) {
        List<Stat> grannyStats = granny.getStats();
        Random random = new Random();
        while (daysSinceLastVisit > 0) {
            daysSinceLastVisit--;
            Stat randomStat = grannyStats.get(random.nextInt(3));
            randomStat.decrementStat();
        }
        shouldGrannyRetire(granny);
    }

    private void shouldGrannyRetire(Granny granny) {
        Stat mood = granny.getMood();
        Stat health = granny.getHealth();
        Stat environment = granny.getEnvironment();
        if (mood == Mood.GRUMPY && health == Health.SICK && environment == Environment.IN_RUINS) {
            granny.setRetired(true);
        }
    }

    public void feedPie(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        Stat health = granny.getHealth();
        health.incrementStat();
    }

    public void playMahjong(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        Stat mood = granny.getMood();
        mood.incrementStat();
    }

    public void cleanHouse(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        Stat environment = granny.getEnvironment();
        environment.incrementStat();
    }

}
