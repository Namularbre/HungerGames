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

    public static State stateFromString(String stringState) {
        try {
            stringState = stringState.toLowerCase();
            return stringToStateMap.get(stringState);
        } catch (Exception exception) {
            return null;
        }
    }

    public static State currentState;

    public static void init() {
        currentState = State.NOT_STARTED;

        stringToStateMap = new HashMap<>();

        stringToStateMap.put("not_started", State.NOT_STARTED);
        stringToStateMap.put("starting", State.STARTING);
        stringToStateMap.put("playing", State.PLAYING);
        stringToStateMap.put("ended", State.ENDED);
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentStep(State state) {
        currentState = state;
    }
}
