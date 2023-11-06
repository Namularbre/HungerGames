package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

public class ChangeHungerGamesStateToStartingTasks extends BukkitRunnable {
    private int secondsLeft;
    private boolean finished;

    public ChangeHungerGamesStateToStartingTasks() {
        this.secondsLeft = HungerGamesConfiguration.SECOND_BEFORE_STARTING_GAME;
        this.finished = false;
    }

    @Override
    public void run() {
        if (finished) {
            return;
        }

        if (PlayerManager.getAlivePlayers().size() >= HungerGamesConfiguration.MINIMAL_AMOUNT_OF_PLAYER) {
            if (this.secondsLeft > 0) {
                secondsLeft--;

                if (this.secondsLeft % 5 == 0 || this.secondsLeft == 3 || this.secondsLeft == 2 || this.secondsLeft == 1) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Enough players are connected, starting in " + secondsLeft + " seconds");
                }
            } else if (this.secondsLeft == 0) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "May the odds be ever in your favor!");
                KitManager.giveKitToPlayers();
                HungerGameStateManager.setStarting();
                PlayerManager.teleportAllPlayersToSpawn();
                this.finished = true;
            }
        }
    }
}
