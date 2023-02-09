package com.codecool.grannymanager.model.enumgrannyproperties;

import com.codecool.grannymanager.service.constans.GrannyConstants;

public enum Environment {
    IN_RUINS(1),
    MESSY(2),
    TIDY(3);

    Environment(int statValue) {
        this.statValue = statValue;
    }

    private int statValue;
    public Environment decrementEnvironment() {
        return values()[Math.max(GrannyConstants.GRANNY_MIN_STAT_VALUE, ordinal()-1)];
    }

    public Environment incrementEnvironment() {
        return values()[Math.min(GrannyConstants.GRANNY_MAX_STAT_VALUE, ordinal()+1)];
    }
}
