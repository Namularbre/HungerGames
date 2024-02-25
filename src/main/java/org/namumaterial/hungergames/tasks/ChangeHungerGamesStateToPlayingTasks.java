package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeHungerGamesStateToPlayingTasks extends BukkitRunnable {

    private boolean finished;
    private int secondLeft;
    private final List<Integer> lastThreeSeconds;

    public ChangeHungerGamesStateToPlayingTasks() {
        this.lastThreeSeconds = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3}));
        this.secondLeft = HungerGamesConfiguration.SECOND_BEFORE_PVP;
        this.finished = false;
    }

    @Override
    public void run() {
        if (this.finished) {
            return;
        }

        if (HungerGameStateManager.gameIsStarting()) {
            if (timeLeft()) {
                this.secondLeft--;

                if (secondLeft % 5 == 0 || this.lastThreeSeconds.contains(this.secondLeft)) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + this.secondLeft + " seconds remaining before Pvp is enabled !");
                }
            } else {
                this.finished = true;
                HungerGameStateManager.setPlaying();
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Pvp is activated.");
            }
        }
    }

    private boolean timeLeft() {
        return this.secondLeft > 0;
    }
}
