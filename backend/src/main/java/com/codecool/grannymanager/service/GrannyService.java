package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.model.enumgrannyproperties.Environment;
import com.codecool.grannymanager.model.enumgrannyproperties.Health;
import com.codecool.grannymanager.model.enumgrannyproperties.Mood;
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

    private List<Consumer<Granny>> events = new ArrayList<>();

    @Autowired
    public GrannyService(GrannyRepository grannyRepository) {
        this.grannyRepository = grannyRepository;
    }

    public void registerGranny(Granny granny){
        grannyRepository.save(granny);
    }

    public Granny visitGranny(Granny granny) {
        checkOnGranny(granny);
        granny.setLastVisit(LocalDateTime.now());
        return grannyRepository.save(granny);
    }


    private void checkOnGranny(Granny granny) {
        int daysPastSinceLastVisit = extractDaysSinceLastVisit(granny.getLastVisit());
        for (int i = 0; i < daysPastSinceLastVisit; i++) {
            decreaseRandomStat(granny);
        }
        retireGranny(granny);
    }

    private int getRandomStatIndex() {
        Random random = new Random();
        return random.nextInt(0,events.size());
    }

    private void retireGranny(Granny granny) {
        if (granny.getEnvironmentStat().equals(Environment.IN_RUINS) && granny.getMoodStat().equals(Mood.GRUMPY) && granny.getHealthStat().equals(Health.SICK)) {
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

    private void initializeConsumerList() {
        Consumer<Granny> healthDecrease = this::decrementHealthStat;
        Consumer<Granny> moodDecrease = this::decrementMoodStat;
        Consumer<Granny> environmentDecrease = this::decrementEnvironmentStat;
        this.events = List.of(healthDecrease, moodDecrease,environmentDecrease);
    }

    private void decreaseRandomStat(Granny granny) {
        Consumer<Granny> randomEvent = events.get(getRandomStatIndex());
        randomEvent.accept(granny);
    }

    private void decrementHealthStat(Granny granny) {
        granny.setHealthStat(granny.getHealthStat().decrementHealth());
    }

    private void incrementHealthStat(Granny granny) {
        granny.setHealthStat(granny.getHealthStat().incrementHealth());
    }

    private void incrementMoodStat(Granny granny) {
        granny.setMoodStat(granny.getMoodStat().incrementMood());
    }

    private void decrementMoodStat(Granny granny) {
        granny.setMoodStat(granny.getMoodStat().decrementMood());
    }

    private void incrementEnvironmentStat(Granny granny) {
        granny.setEnvironmentStat(granny.getEnvironmentStat().incrementEnvironment());
    }

    private void decrementEnvironmentStat(Granny granny) {
        granny.setEnvironmentStat(granny.getEnvironmentStat().decrementEnvironment());
    }



    public void feedGranny(Granny granny) {
        incrementHealthStat(granny);
        grannyRepository.save(granny);
    }

    public void cleanHouse(Granny granny) {
        incrementEnvironmentStat(granny);
        grannyRepository.save(granny);
    }

    public void playMahjongWithGranny(Granny granny) {
        incrementMoodStat(granny);
        grannyRepository.save(granny);
    }
}
