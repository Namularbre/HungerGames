package org.namumaterial.hungergames.managers;

public class HungerGameStateManager {
    public enum State {
        NOT_STARTED,
        STARTING,
        PLAYING,
        ENDED,
    }

    public static State currentState;

    public static void init() {
        currentState = State.NOT_STARTED;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentStep(State state) {
        currentState = state;
    }
}
