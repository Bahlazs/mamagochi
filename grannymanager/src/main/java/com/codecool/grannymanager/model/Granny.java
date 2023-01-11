package com.codecool.grannymanager.model;

import com.codecool.grannymanager.model.grannyproperties.Environment;
import com.codecool.grannymanager.model.grannyproperties.Mood;
import com.codecool.grannymanager.model.grannyproperties.Health;
import com.codecool.grannymanager.model.grannyproperties.Stat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Granny {

    private final int MOODINDEX = 1;
    private final int HEALTINDEX = 2;
    private final int ENVIROMENTINDEX = 0;

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    private int userId;

    private String name;

    private int age;

    private LocalDateTime lastVisit;

    private List<Stat> stats;

    private boolean retired = false;


    public Granny(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.age = 60;
        this.lastVisit = LocalDateTime.now();
        this.stats = new ArrayList<>();
        initializeStats();
    }

    private void initializeStats() {
        this.stats.add(Environment.TIDY);
        this.stats.add(Mood.HAPPY);
        this.stats.add(Health.HEALTHY);
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

    public List<Stat> getStats() {
        return stats;
    }

    public Stat getHealth() {
        return stats.get(HEALTINDEX);
    }

    public Stat getMood() {
        return stats.get(MOODINDEX);
    }

    public Stat getEnvironment() {
        return stats.get(ENVIROMENTINDEX);
    }

}
