package com.codecool.grannymanager.model.enumgrannyproperties;

import com.codecool.grannymanager.service.constans.GrannyConstants;

public enum Health {
    SICK(1),
    WEAK(2),
    HEALTHY(3);

    private int statValue;

    Health(int statValue) {
        this.statValue = statValue;
    }

    public Health decrementHealth() {
        return values()[Math.max(GrannyConstants.GRANNY_MIN_STAT_VALUE, ordinal()-1)];
    }

    public Health incrementHealth() {
        return values()[Math.min(GrannyConstants.GRANNY_MAX_STAT_VALUE, ordinal()+1)];
    }
}
