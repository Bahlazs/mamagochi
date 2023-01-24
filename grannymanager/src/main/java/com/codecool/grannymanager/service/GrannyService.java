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

    public void createGranny(int userId, String name) {
        grannyRepository.createGranny(userId, name);
    }


    public Granny visitGranny(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        checkOnGranny(granny);
        granny.setLastVisit(LocalDateTime.now());

        return granny;
    }

    private void checkOnGranny(Granny granny) {
        decrementGrannyStatByNotVisitedDays(granny);
        shouldGrannyRetire(granny);
    }

    private void decrementGrannyStatByNotVisitedDays(Granny granny) {
        LocalDateTime lastVisit = granny.getLastVisit();
        int daysSinceLastVisit = extractDaysSinceLastVisit(lastVisit);

        while (daysSinceLastVisit > 0) {
            daysSinceLastVisit--;
            decrementRandomStat(granny);
        }
    }

    private int extractDaysSinceLastVisit(LocalDateTime lastVisit) {
        int hoursSinceLastVisit = extractHoursSinceLastVisit(lastVisit);
        return Math.floorDiv(hoursSinceLastVisit, 24);
    }

    private int extractHoursSinceLastVisit(LocalDateTime lastVisit) {
        return (int) ChronoUnit.HOURS.between(lastVisit, LocalDateTime.now());
    }

    private void decrementRandomStat(Granny granny) {
        int randomNum = (int) ((Math.random() * (3 - 1)) + 1);
        switch (randomNum) {
            case 1 -> decrementHealthStat(granny);
            case 2 -> decrementEnvironmentStat(granny);
            case 3 -> decrementMoodStat(granny);
        }
    }

    private void decrementHealthStat(Granny granny) {
        granny.getHealth().decrementStat();
    }

    private void decrementMoodStat(Granny granny) {
        granny.getMood().decrementStat();
    }

    private void decrementEnvironmentStat(Granny granny) {
        granny.getEnvironment().decrementStat();
    }

    private void shouldGrannyRetire(Granny granny) {
        if (checkGrannyStatsIfLowest(granny)) {
            granny.setRetired(true);
        }
    }

    private boolean checkGrannyStatsIfLowest(Granny granny) {
        return granny.getMood().getStat() == 0 && granny.getHealth().getStat() == 0 && granny.getEnvironment().getStat() == 0;
    }

    public Granny feedPie(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.getHealth().incrementStat();
        return granny;
    }

    public Granny playMahjong(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.getMood().incrementStat();
        return granny;
    }

    public Granny cleanHouse(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        granny.getEnvironment().incrementStat();
        return granny;
    }

    public void jumpOneDay(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        LocalDateTime lastVisit = granny.getLastVisit();
        granny.setLastVisit(lastVisit.minusDays(1));

    }
    public void jumpOneWeek(int id) {
        Granny granny = grannyRepository.findGrannyById(id);
        LocalDateTime lastVisit = granny.getLastVisit();
        granny.setLastVisit(lastVisit.minusDays(7));

    }

}
