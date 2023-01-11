package com.codecool.grannymanager.model;

import com.codecool.grannymanager.model.grannyproperties.Environment;
import com.codecool.grannymanager.model.grannyproperties.Mood;
import com.codecool.grannymanager.model.grannyproperties.Health;
import com.codecool.grannymanager.model.grannyproperties.Stat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Granny {

    private int userId;

    private String name;

    private int age;

    private LocalDateTime lastVisit;


    private Stat health;
    private Stat mood;
    private Stat environment;

    private boolean retired = false;


    public Granny(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.age = 60;
        this.lastVisit = LocalDateTime.now();
        initializeStats();
    }

    private void initializeStats() {
        this.health = Health.HEALTHY;
        this.mood = Mood.HAPPY;
        this.environment = Environment.TIDY;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Stat getHealth() {
        return health;
    }

    public void setHealth(Stat health) {
        this.health = health;
    }

    public Stat getMood() {
        return mood;
    }

    public void setMood(Stat mood) {
        this.mood = mood;
    }

    public Stat getEnvironment() {
        return environment;
    }

    public void setEnvironment(Stat environment) {
        this.environment = environment;
    }
}
