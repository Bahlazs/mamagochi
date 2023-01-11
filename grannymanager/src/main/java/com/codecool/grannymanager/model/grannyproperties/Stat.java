package com.codecool.grannymanager.model.grannyproperties;

public interface Stat {

    Stat incrementStat();

    Stat decrementStat();

    int MAX = 0;
    int MIN = 2;

}
