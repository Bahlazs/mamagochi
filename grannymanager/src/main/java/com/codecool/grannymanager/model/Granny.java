package com.codecool.grannymanager.model;

import com.codecool.grannymanager.model.grannyproperties.Environment;
import com.codecool.grannymanager.model.grannyproperties.Mood;
import com.codecool.grannymanager.model.grannyproperties.Health;

import java.time.LocalDateTime;
public class Granny {

    public static final int START_AGE = 60;
    private final int userId;
    private String name;
    private int age;
    private LocalDateTime lastVisit;
    private final Environment environmentStat;
    private final Health healthStat;
    private final Mood moodStat;

    private boolean retired = false;

    public Granny(int userId, String name) {
        this(userId, name, new Environment(), new Health(), new Mood());
    }

    public Granny(int userId, String name, Environment environmentStat, Health healthStat, Mood moodStat) {
        this.userId = userId;
        this.name = name;
        this.age = START_AGE;
        this.lastVisit = LocalDateTime.now();
        this.environmentStat = environmentStat;
        this.healthStat = healthStat;
        this.moodStat = moodStat;
    }

    public int getUserId() {
        return userId;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }


    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public Health getHealth() {
        return healthStat;
    }

    public void setHealth(int health) {
        this.healthStat.setStat(health);
    }

    public Mood getMood() {
        return moodStat;
    }

    public void setMood(int mood) {
        this.moodStat.setStat(mood);
    }

    public Environment getEnvironment() {
        return environmentStat;
    }

    public void setEnvironment(int environment) {
        this.environmentStat.setStat(environment);
    }
}
