package org.namumaterial.hungergames.managers;

import org.bukkit.plugin.Plugin;
import org.namumaterial.hungergames.tasks.ArenaTasks;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class TaskManager {

    public static void runTasks(Plugin plugin) {
        final int PERIOD = SecondToTicksConverter.convert(10);
        final int DELAY = 0;

        ArenaTasks arenaTasks = new ArenaTasks();
        arenaTasks.runTaskTimer(plugin, DELAY, PERIOD);
    }

}
