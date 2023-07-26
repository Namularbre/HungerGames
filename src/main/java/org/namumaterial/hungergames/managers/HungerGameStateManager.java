package org.namumaterial.hungergames.managers;

import java.util.HashMap;

public class HungerGameStateManager {
    public enum State {
        NOT_STARTED,
        STARTING,
        PLAYING,
        ENDED,
    }

    private static HashMap<String, State> stringToStateMap;
    public static State currentState;

    public static State stateFromString(String stringState) {
        try {
            stringState = stringState.toLowerCase();
            return stringToStateMap.get(stringState);
        } catch (Exception exception) {
            return null;
        }
    }

    public static void init() {
        currentState = State.NOT_STARTED;

        stringToStateMap = new HashMap<>();

        stringToStateMap.put("not_started", State.NOT_STARTED);
        stringToStateMap.put("starting", State.STARTING);
        stringToStateMap.put("playing", State.PLAYING);
        stringToStateMap.put("ended", State.ENDED);
    }

    public static boolean gameIsNotStarted() {
        return currentState == State.NOT_STARTED;
    }

    public static boolean gameIsStarting() {
        return currentState == State.STARTING;
    }

    public static boolean gameIsPlaying() {
        return currentState == State.PLAYING;
    }

    public static boolean gameIsEnded() {
        return currentState == State.ENDED;
    }

    public static boolean gameIsLaunched() {
        return gameIsPlaying() || gameIsStarting();
    }

    public static void setCurrentStep(State state) {
        currentState = state;
    }

}
