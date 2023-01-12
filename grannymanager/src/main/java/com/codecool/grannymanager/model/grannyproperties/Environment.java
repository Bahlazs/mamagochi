package com.codecool.grannymanager.model.grannyproperties;

public enum Environment implements Stat {

    TIDY("Tidy"), MESSY("Messy"), IN_RUINS("In ruins");

    private String stringValueOfEnum;
    @Override
    public String getStringValueOfEnum() {
        return stringValueOfEnum;
    }

    Environment(String stringValueOfEnum) {
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
