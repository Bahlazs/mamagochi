package com.codecool.grannymanager.model.enumgrannyproperties;

import com.codecool.grannymanager.service.constans.GrannyConstants;

public enum Mood {
    GRUMPY(1),
    BORED(2),
    HAPPY(3);

    private int statValue;

    Mood(int statValue) {
        this.statValue = statValue;
    }

    public Mood decrementMood() {
        return values()[Math.max(GrannyConstants.GRANNY_MIN_STAT_VALUE, ordinal()-1)];
    }

    public Mood incrementMood() {
        return values()[Math.min(GrannyConstants.GRANNY_MAX_STAT_VALUE, ordinal()+1)];
    }

}
