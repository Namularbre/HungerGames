package org.namumaterial.hungergames.utils;

public class SecondToTicksConverter {
    private static final int TICK_IN_SECONDS = 20;

    public static int convert(int seconds) { return seconds * TICK_IN_SECONDS; }

}
