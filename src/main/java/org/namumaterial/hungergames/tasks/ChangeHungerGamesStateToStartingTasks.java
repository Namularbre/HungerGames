package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class ChangeHungerGamesStateToStartingTasks extends BukkitRunnable {
    private int ticksLeft;
    private boolean finished;
    public ChangeHungerGamesStateToStartingTasks() {
        this.ticksLeft = HungerGamesConfiguration.SECOND_BEFORE_STARTING_GAME_WHEN_ENOUGH_PLAYER;
        this.finished = false;
    }

    @Override
    public void run() {
        if (finished) {
            return;
        }

        if (TributeManager.getAlivePlayers().size() >= HungerGamesConfiguration.MINIMAL_AMOUNT_OF_PLAYER) {
            if (this.ticksLeft > 0) {
                ticksLeft--;
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Enough players are connected, starting in " + ticksLeft + " seconds");
            } else if (this.ticksLeft == 0) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "May the odds be ever in your favor!");
                KitManager.giveKitToPlayers();
                HungerGameStateManager.setStarting();
                this.finished = true;
            }
        }
    }

    private int ticksToSecond(int ticks) {
        return ticks / 20;
    }
}
