package org.namumaterial.hungergames.managers;

import org.bukkit.plugin.Plugin;
import org.namumaterial.hungergames.tasks.ArenaTasks;
import org.namumaterial.hungergames.tasks.GiftTasks;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class TaskManager {

    public static void runTasks(Plugin plugin) {
        final int PERIOD = SecondToTicksConverter.convert(10);
        final int DELAY = 0;

        ArenaTasks arenaTasks = new ArenaTasks();
        GiftTasks giftTasks = new GiftTasks();

        //Run unconditional tasks
        arenaTasks.runTaskTimer(plugin, DELAY, PERIOD);

        if (gameIsLaunched()) {
            giftTasks.runTaskTimer(plugin, DELAY, PERIOD);
        }
    }

    private static boolean gameIsLaunched() {
        return HungerGameStateManager.currentState == HungerGameStateManager.State.PLAYING || HungerGameStateManager.currentState == HungerGameStateManager.State.STARTING;
    }

}
