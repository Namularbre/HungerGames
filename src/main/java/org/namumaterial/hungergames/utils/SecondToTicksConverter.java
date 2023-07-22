package org.namumaterial.hungergames.utils;

public class SecondToTicksConverter {
    public static double convert(double seconds) {
        return seconds * 20.0;
    }

    public static int convert(int seconds) { return seconds * 20; }

}
