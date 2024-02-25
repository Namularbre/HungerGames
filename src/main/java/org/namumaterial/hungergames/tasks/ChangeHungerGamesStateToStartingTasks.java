package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeHungerGamesStateToStartingTasks extends BukkitRunnable {
    private int secondsLeft;
    private boolean finished;

    private final List<Integer> lastThreeSeconds;

    public ChangeHungerGamesStateToStartingTasks() {
        this.lastThreeSeconds = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3}));

        this.secondsLeft = HungerGamesConfiguration.SECOND_BEFORE_STARTING_GAME;
        this.finished = false;
    }

    @Override
    public void run() {
        if (finished) {
            return;
        }

        if (PlayerManager.getAlivePlayers().size() >= HungerGamesConfiguration.MINIMAL_AMOUNT_OF_PLAYER) {
            if (timeLeft()) {
                secondsLeft--;

                if (this.secondsLeft % 5 == 0 || this.lastThreeSeconds.contains(this.secondsLeft)) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Enough players are connected, starting in " + secondsLeft + " seconds");
                }
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "May the odds be ever in your favor!");
                HungerGameStateManager.setStarting();
                this.finished = true;
            }
        }
    }

    private boolean timeLeft() {
        return this.secondsLeft > 0;
    }
}
