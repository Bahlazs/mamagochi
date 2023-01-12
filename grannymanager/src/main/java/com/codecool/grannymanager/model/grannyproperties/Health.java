package com.codecool.grannymanager.model.grannyproperties;

public enum Health implements Stat {

    HEALTHY("Healthy"),WEAK("Weak"),SICK("Sick");

    private String stringValueOfEnum;
    @Override
    public String getStringValueOfEnum() {
        return stringValueOfEnum;
    }

    Health(String stringValueOfEnum) {
        this.stringValueOfEnum = stringValueOfEnum;
    }

    @Override
    public Stat incrementStat() {
        Stat statChangeTo = this;
        if (ordinal() > 0) {
            statChangeTo = values()[ordinal() - 1];
        }
        return statChangeTo;
    }

    @Override
    public Stat decrementStat() {
        Stat statChangeTo = this;
        if (ordinal() < values().length - 1) {
            statChangeTo = values()[ordinal() + 1];
        }
        return statChangeTo;
    }
}
