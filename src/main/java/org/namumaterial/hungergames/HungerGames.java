package org.namumaterial.hungergames;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import org.namumaterial.hungergames.listeners.PlayerCompassListener;
import org.namumaterial.hungergames.listeners.PlayerConnectionListener;
import org.namumaterial.hungergames.listeners.PlayerDeathListener;
import org.namumaterial.hungergames.listeners.ShieldCraftListener;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.ItemManager;
import org.namumaterial.hungergames.managers.TaskManager;
import org.namumaterial.hungergames.utils.Arena;
import org.namumaterial.hungergames.utils.GeneralConfig;

public final class HungerGames extends JavaPlugin {

    public static GeneralConfig generalConfig;
    public static Arena arena;

    @Override
    public void onEnable() {
        getLogger().info("Initialising items...");
        ItemManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising arena...");
        final double ARENA_RADIUS = 1000.0;
        final Location SPAWN_LOCATION = getServer().getWorld("world").getSpawnLocation();

        arena = new Arena(SPAWN_LOCATION, ARENA_RADIUS);
        getLogger().info("Done");

        getLogger().info("Initialising plugin's state manager...");
        HungerGameStateManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising listeners...");
        getServer().getPluginManager().registerEvents(new PlayerCompassListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldCraftListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getLogger().info("Done");

        getLogger().info("Initialising tasks...");
        TaskManager.runTasks(this);
        getLogger().info("Done");
    }

    @Override
    public void onDisable() {
        getLogger().info("Hunger games plugin is disabled");
    }
}
