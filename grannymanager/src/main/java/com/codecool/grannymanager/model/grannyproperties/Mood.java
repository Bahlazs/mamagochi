package com.codecool.grannymanager.model.grannyproperties;

public enum Mood implements Stat {

    HAPPY,BORED,GRUMPY;

    @Override
    public Stat incrementStat() {
        Stat statChangeTo = this;
        if (ordinal() != 0) {
            statChangeTo = values()[ordinal() - 1];
        }
        return statChangeTo;
    }

    @Override
    public Stat decrementStat() {
        Stat statChangeTo = this;
        if (ordinal() != values().length) {
            statChangeTo = values()[ordinal() + 1];
        }
        return statChangeTo;
    }
}
