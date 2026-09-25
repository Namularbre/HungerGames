package org.namumaterial.hungergames.utils;

public class SecondToTicksConverter {
    private static final int TICK_IN_SECONDS = 20;

    public static int convert(int seconds) { return seconds * TICK_IN_SECONDS; }

    // For durations like 1.5 seconds, rounded to the nearest tick
    public static int convert(double seconds) { return (int) Math.round(seconds * TICK_IN_SECONDS); }

}
