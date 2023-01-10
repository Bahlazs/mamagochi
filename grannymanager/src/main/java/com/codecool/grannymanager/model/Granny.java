package com.codecool.grannymanager.model;

import com.codecool.grannymanager.model.grannyproperties.Enviroment;
import com.codecool.grannymanager.model.grannyproperties.Feeling;
import com.codecool.grannymanager.model.grannyproperties.Health;
import com.codecool.grannymanager.model.grannyproperties.Stat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Granny {

    private int userId;

    private String name;

    private int age;

    private LocalDateTime lastVisit;

    private List<Stat> stats;

    public Granny(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.age = 60;
        this.lastVisit = LocalDateTime.now();
        this.stats = new ArrayList<>();
        initializeStats();
    }

    private void initializeStats(){
        this.stats.add(Enviroment.TIDY);
        this.stats.add(Feeling.HAPPY);
        this.stats.add(Health.HEALTHY);
    }





}
