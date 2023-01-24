package com.codecool.grannymanager.model.grannyproperties;

import lombok.Getter;

@Getter
public abstract class Stat {

    private int stat;

    protected String[] values;

    public Stat() {
        this.stat = 0;
        this.values = new String[3];
    }

    public void setStat (int stat) throws IllegalArgumentException {
        if (stat < 0 || stat > values.length) {
            throw new IllegalArgumentException("Can't set stat to that value");
        }
        this.stat = stat;
    }

    public String getStringValueOfStat() {
        return values[stat];
    }

    public void incrementStat() {
        if (this.stat < 2) {
            this.stat++;
        }
    }

    public void decrementStat() {
        if (this.stat > 0) {
            this.stat--;
        }
    }
}
