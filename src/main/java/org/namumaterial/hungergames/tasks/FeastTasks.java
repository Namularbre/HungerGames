package org.namumaterial.hungergames.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.FeastManager;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

public class FeastTasks extends BukkitRunnable {
    private int numberOfFeastSpawned;

    public FeastTasks() {
        this.numberOfFeastSpawned = 0;
    }

    @Override
    public void run() {
        final int UNLIMITED_NUMBER_OF_FEAST = -1;
        if (HungerGameStateManager.gameIsPlaying()) {
            if (HungerGamesConfiguration.NUMBER_OF_FEAST == UNLIMITED_NUMBER_OF_FEAST || HungerGamesConfiguration.NUMBER_OF_FEAST > numberOfFeastSpawned) {
                this.numberOfFeastSpawned++;
                FeastManager.placeFeast();
            }
        }
    }
}
