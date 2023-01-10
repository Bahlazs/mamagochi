package com.codecool.grannymanager.model.grannyproperties;

public enum Mood implements Stat {

    HAPPY,BORED,GRUMPY;

    @Override
    public Stat incrementStat() {
        Stat statChangeTo;
        if(ordinal() == MAX){
            statChangeTo = this;
        } else{
            statChangeTo = values()[ordinal() - 1];
        }
        return statChangeTo;
    }

    @Override
    public Stat decrementStat() {
        Stat statChangeTo;
        if(ordinal() == MIN){
            statChangeTo = this;
        } else{
            statChangeTo = values()[ordinal() + 1];
        }
        return statChangeTo;

    }
}
