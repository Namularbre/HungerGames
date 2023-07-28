package org.namumaterial.hungergames.managers;

import org.bukkit.plugin.Plugin;
import org.namumaterial.hungergames.tasks.ArenaTasks;
import org.namumaterial.hungergames.tasks.ChangeHungerGamesStateToPlayingTasks;
import org.namumaterial.hungergames.tasks.ChangeHungerGamesStateToStartingTasks;
import org.namumaterial.hungergames.tasks.GiftTasks;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class TaskManager {

    public static void runTasks(Plugin plugin) {
        final int TEN_SECOND_PERIOD = SecondToTicksConverter.convert(10);
        final int ONE_SECOND_PERIOD = SecondToTicksConverter.convert(1);
        final int DELAY = 0;

        ArenaTasks arenaTasks = new ArenaTasks();
        GiftTasks giftTasks = new GiftTasks();

        ChangeHungerGamesStateToStartingTasks changeHungerGamesStateToStartingTasks = new ChangeHungerGamesStateToStartingTasks();
        ChangeHungerGamesStateToPlayingTasks changeHungerGamesStateToPlayingTasks = new ChangeHungerGamesStateToPlayingTasks();

        arenaTasks.runTaskTimer(plugin, DELAY, TEN_SECOND_PERIOD);
        giftTasks.runTaskTimer(plugin, DELAY, TEN_SECOND_PERIOD);

        changeHungerGamesStateToStartingTasks.runTaskTimer(plugin, DELAY, ONE_SECOND_PERIOD);
        changeHungerGamesStateToPlayingTasks.runTaskTimer(plugin, DELAY, ONE_SECOND_PERIOD);
    }

}
