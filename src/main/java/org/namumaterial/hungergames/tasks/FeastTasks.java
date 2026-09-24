package org.namumaterial.hungergames.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.FeastManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

public class FeastTasks extends BukkitRunnable {
    private int numberOfFeastSpawned;

    public FeastTasks() {
        this.numberOfFeastSpawned = 0;
    }

    @Override
    public void run() {
        final int UNLIMITED_NUMBER_OF_FEAST = -1;

        if (HungerGamesConfiguration.NUMBER_OF_FEAST != UNLIMITED_NUMBER_OF_FEAST && this.numberOfFeastSpawned >= HungerGamesConfiguration.NUMBER_OF_FEAST) {
            cancel();
            return;
        }

        this.numberOfFeastSpawned++;
        FeastManager.placeFeast();
    }
}
