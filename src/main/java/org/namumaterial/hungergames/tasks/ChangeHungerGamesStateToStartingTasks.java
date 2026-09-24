package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeHungerGamesStateToStartingTasks extends BukkitRunnable {
    private int secondsLeft;

    private final List<Integer> lastThreeSeconds;

    public ChangeHungerGamesStateToStartingTasks() {
        this.lastThreeSeconds = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3}));

        this.secondsLeft = HungerGamesConfiguration.SECOND_BEFORE_STARTING_GAME;
    }

    @Override
    public void run() {
        if (isEnoughPlayersToStart()) {
            if (timeLeft()) {
                secondsLeft--;

                if (this.secondsLeft % 5 == 0 || this.lastThreeSeconds.contains(this.secondsLeft)) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Enough players are connected, starting in " + secondsLeft + " seconds");
                }
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "May the odds be ever in your favor!");
                // Cancels this task and starts the grace period tasks
                HungerGameStateManager.setStarting();
            }
        }
    }

    private static boolean isEnoughPlayersToStart() {
        return TributeManager.getNumberOfTributes() >= HungerGamesConfiguration.MINIMAL_AMOUNT_OF_PLAYER;
    }

    private boolean timeLeft() {
        return this.secondsLeft > 0;
    }
}
