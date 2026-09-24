package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.namumaterial.hungergames.tasks.*;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final int NO_DELAY = 0;
    private static final int ONE_SECOND_PERIOD = SecondToTicksConverter.convert(1);
    private static final int TEN_SECOND_PERIOD = SecondToTicksConverter.convert(10);
    private static final int FIVE_HUNDRED_SECOND_PERIOD = SecondToTicksConverter.convert(500);

    private static Plugin plugin;

    // Tasks of the current game phase. They are all cancelled when the game changes state.
    private static final List<BukkitTask> phaseTasks = new ArrayList<>();

    public static void init(Plugin hungerGamesPlugin) {
        plugin = hungerGamesPlugin;

        new DisplayHelpTask().runTaskTimer(plugin, NO_DELAY, FIVE_HUNDRED_SECOND_PERIOD);

        startLobbyPhase();
    }

    // NOT_STARTED : waiting for enough players, then countdown before the game starts. Players don't get hungry.
    public static void startLobbyPhase() {
        cancelPhaseTasks();

        schedule(new ChangeHungerGamesStateToStartingTasks(), NO_DELAY, ONE_SECOND_PERIOD);
        schedule(new KeepPlayersFedTask(), NO_DELAY, ONE_SECOND_PERIOD);
    }

    // STARTING : grace period, gifts are given, countdown before pvp
    public static void startGracePhase() {
        cancelPhaseTasks();

        schedule(new GiftTasks(), NO_DELAY, TEN_SECOND_PERIOD);
        schedule(new ChangeHungerGamesStateToPlayingTasks(), NO_DELAY, ONE_SECOND_PERIOD);
    }

    // PLAYING : pvp is on, gifts and feasts are given
    public static void startPvpPhase() {
        cancelPhaseTasks();

        final int SECONDS_BETWEEN_FEAST = SecondToTicksConverter.convert(HungerGamesConfiguration.SECONDS_BETWEEN_FEAST);

        schedule(new GiftTasks(), NO_DELAY, TEN_SECOND_PERIOD);
        schedule(new FeastTasks(), SECONDS_BETWEEN_FEAST, SECONDS_BETWEEN_FEAST);
    }

    // ENDED by a victory : the server stops, and the start script resets the map and starts it again.
    // It is a phase task, so going back to the lobby with /setstate cancels the shutdown.
    public static void scheduleServerShutdown() {
        final int SECONDS_BEFORE_SHUTDOWN = 15;

        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The server will restart in " + SECONDS_BEFORE_SHUTDOWN + " seconds");

        phaseTasks.add(Bukkit.getScheduler().runTaskLater(plugin, Bukkit::shutdown, SecondToTicksConverter.convert(SECONDS_BEFORE_SHUTDOWN)));
    }

    // ENDED (or any state without tasks)
    public static void cancelPhaseTasks() {
        for (BukkitTask task: phaseTasks) {
            task.cancel();
        }

        phaseTasks.clear();
    }

    private static void schedule(BukkitRunnable task, int delay, int period) {
        phaseTasks.add(task.runTaskTimer(plugin, delay, period));
    }
}
