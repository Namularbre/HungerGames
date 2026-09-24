package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;

import java.util.HashMap;
import java.util.List;

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

    public static boolean gameIsLaunched() {
        return gameIsPlaying() || gameIsStarting();
    }

    public static void setCurrentStep(State state) {
        switch (state) {
            case NOT_STARTED:
                setNotStarted();
                break;
            case STARTING:
                setStarting();
                break;
            case PLAYING:
                setPlaying();
                break;
            case ENDED:
                setEnded();
                break;
        }
    }

    public static void setNotStarted() {
        TributeManager.reset();
        // Registers the players back as tributes
        PlayerManager.setPlayersAsNotStartedState();
        HungerGames.arena.reset();
        currentState = State.NOT_STARTED;
        TaskManager.startLobbyPhase();
    }

    public static void setStarting() {
        PlayerManager.removeKitSelectorFromInventory();
        KitManager.giveKitToPlayers();
        PlayerManager.teleportAllPlayersToSpawn();
        currentState = State.STARTING;
        HungerGames.arena.startShrinking();
        TaskManager.startGracePhase();
    }

    public static void setPlaying() {
        currentState = State.PLAYING;
        // Already shrinking if the game went through STARTING, needed when forced with /setstate
        HungerGames.arena.startShrinking();
        TaskManager.startPvpPhase();
    }

    public static void setEnded() {
        currentState = State.ENDED;
        HungerGames.arena.stopShrinking();
        TaskManager.cancelPhaseTasks();
    }

    public static String currentStateToString() {
        for (String stringState: stringToStateMap.keySet()) {
            if (currentState == stringToStateMap.get(stringState)) {
                return stringState;
            }
        }

        return "ERROR_STATE_NOT_FOUND";
    }

    // To call after a tribute is removed (death or disconnection)
    public static boolean checkForWinner() {
        if (!gameIsLaunched()) {
            return false;
        }

        List<Player> remaining = TributeManager.getTributePlayers();

        if (remaining.size() > 1) {
            return false;
        }

        setEnded();
        if (remaining.size() == 1) {
            Bukkit.broadcastMessage(ChatColor.GOLD + remaining.get(0).getName() + " won the Hunger Games!");
        } else {
            Bukkit.broadcastMessage(ChatColor.GOLD + "GAME OVER - no survivor");
        }
        return true;
    }
}
