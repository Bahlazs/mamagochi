package com.codecool.grannymanager.model.grannyproperties;

public interface Stat {

//    default Stat incrementStat(){
//        Stat statChangeTo = this;
//        if (ordinal() > 0) {
//            statChangeTo = values()[ordinal() - 1];
//        }
//        return statChangeTo;
//    };

    Stat incrementStat();
    Stat decrementStat();

    String getStringValueOfEnum();
}
