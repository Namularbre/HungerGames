package org.namumaterial.hungergames.managers;

import org.bukkit.plugin.Plugin;
import org.namumaterial.hungergames.tasks.*;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class TaskManager {

    public static void runTasks(Plugin plugin) {
        final int FIVE_HUNDRED_PERIOD = SecondToTicksConverter.convert(500);
        final int TEN_SECOND_PERIOD = SecondToTicksConverter.convert(10);
        final int ONE_SECOND_PERIOD = SecondToTicksConverter.convert(1);
        final int SECOND_BETWEEN_FEAST = SecondToTicksConverter.convert(HungerGamesConfiguration.SECONDS_BETWEEN_FEAST);
        final int DELAY = 0;

        DisplayHelpTask displayHelpTask = new DisplayHelpTask();

        ArenaTasks arenaTasks = new ArenaTasks();
        GiftTasks giftTasks = new GiftTasks();
        FeastTasks feastTasks = new FeastTasks();

        ChangeHungerGamesStateToStartingTasks changeHungerGamesStateToStartingTasks = new ChangeHungerGamesStateToStartingTasks();
        ChangeHungerGamesStateToPlayingTasks changeHungerGamesStateToPlayingTasks = new ChangeHungerGamesStateToPlayingTasks();

        displayHelpTask.runTaskTimer(plugin, DELAY, FIVE_HUNDRED_PERIOD);

        arenaTasks.runTaskTimer(plugin, DELAY, TEN_SECOND_PERIOD);
        giftTasks.runTaskTimer(plugin, DELAY, TEN_SECOND_PERIOD);
        feastTasks.runTaskTimer(plugin, DELAY, SECOND_BETWEEN_FEAST);

        changeHungerGamesStateToStartingTasks.runTaskTimer(plugin, DELAY, ONE_SECOND_PERIOD);
        changeHungerGamesStateToPlayingTasks.runTaskTimer(plugin, DELAY, ONE_SECOND_PERIOD);
    }

}
