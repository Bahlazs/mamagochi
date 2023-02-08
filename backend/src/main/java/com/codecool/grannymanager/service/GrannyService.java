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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@Service
public class GrannyService {

    private final GrannyRepository grannyRepository;

    @Autowired
    public GrannyService(GrannyRepository grannyRepository) {
        this.grannyRepository = grannyRepository;
    }

    public void registerGranny(Granny granny){
        grannyRepository.save(granny);
    }

    public Granny visitGranny(long id) {
        Granny granny = grannyRepository.findGrannyById(id);
        decrementRandomStat(granny);
        granny.setLastVisit(LocalDateTime.now());
        return grannyRepository.save(granny);
    }


    private void decrementRandomStat(Granny granny) {
        int statChanged = 0;
        int numberOfDaysSinceLastVisit = extractDaysSinceLastVisit(granny.getLastVisit());
        String[] stats = new String[] {"environment", "health", "mood"};
            while (statChanged < numberOfDaysSinceLastVisit && !granny.isRetired()) {
                String stat = stats[chooseRandomStatIndex()];

                switch (stat) {
                    case "environment" -> {
                        int environmentStat = granny.getEnvironmentStat();
                        if (environmentStat > 1) {
                            granny.setEnvironmentStat(environmentStat - 1);
                            statChanged++;
                        }
                    }
                    case "health" -> {
                        int healthStat = granny.getHealthStat();
                        if (healthStat > 1) {
                            granny.setHealthStat(healthStat - 1);
                            statChanged++;
                        }
                    }
                    case "mood" -> {
                        int moodStat = granny.getMoodStat();
                        if (moodStat > 1) {
                            granny.setMoodStat(moodStat - 1);
                            statChanged++;
                        }
                    }
                }
                retireGranny(granny);
            }
        }

    private int chooseRandomStatIndex() {
        Random random = new Random();
        return random.nextInt(0,3);
    }

    private void retireGranny(Granny granny) {
        if (granny.getEnvironmentStat() == 1 && granny.getMoodStat() == 1 && granny.getHealthStat() == 1) {
            granny.setRetired(true);
        }
    }

    private int extractDaysSinceLastVisit(LocalDateTime lastVisit) {
        int hoursSinceLastVisit = extractHoursSinceLastVisit(lastVisit);
        return Math.floorDiv(hoursSinceLastVisit, 24);
    }

    private int extractHoursSinceLastVisit(LocalDateTime lastVisit) {
        return (int) ChronoUnit.HOURS.between(lastVisit, LocalDateTime.now());
    }



    public void jumpOneDay(long id) {
        Granny granny = grannyRepository.findGrannyById(id);
        LocalDateTime lastVisit = granny.getLastVisit();
        granny.setLastVisit(lastVisit.minusDays(1));

    }
    public void jumpOneWeek(long id) {
        Granny granny = grannyRepository.findGrannyById(id);
        LocalDateTime lastVisit = granny.getLastVisit();
        granny.setLastVisit(lastVisit.minusDays(7));

    }


    public void feedGranny(Granny granny) {
        granny.setHealthStat(granny.getHealthStat()+1);
        grannyRepository.save(granny);
    }

    public void cleanHouse(Granny granny) {
        granny.setEnvironmentStat(granny.getEnvironmentStat()+1);
        grannyRepository.save(granny);
    }

    public void playMahjongWithGranny(Granny granny) {
        granny.setMoodStat(granny.getMoodStat()+1);
        grannyRepository.save(granny);
    }
}
