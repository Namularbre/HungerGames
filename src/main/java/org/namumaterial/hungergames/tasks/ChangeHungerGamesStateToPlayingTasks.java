package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

public class ChangeHungerGamesStateToPlayingTasks extends BukkitRunnable {

    private boolean finished;
    private int secondLeft;

    public ChangeHungerGamesStateToPlayingTasks() {
        this.secondLeft = HungerGamesConfiguration.SECOND_BEFORE_PVP;
        this.finished = false;
    }

    @Override
    public void run() {
        if (this.finished) {
            return;
        }

        if (HungerGameStateManager.gameIsStarting()) {
            if (this.secondLeft > 0) {
                this.secondLeft--;

                if (secondLeft % 5 == 0 || secondLeft == 3 || secondLeft == 2 || secondLeft == 1) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + this.secondLeft + " seconds remaining before Pvp is enabled !");
                }
            } else if (this.secondLeft == 0) {
                this.finished = true;
                HungerGameStateManager.setPlaying();
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Pvp is activated.");
            }
        }
    }
}
