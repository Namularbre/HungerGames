package org.namumaterial.hungergames;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.namumaterial.hungergames.commands.*;
import org.namumaterial.hungergames.listeners.*;
import org.namumaterial.hungergames.managers.*;
import org.namumaterial.hungergames.utils.Arena;

public final class HungerGames extends JavaPlugin {
    public static Arena arena;

    @Override
    public void onEnable() {
        getLogger().info("Initialising items...");
        ItemManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising kits...");
        KitManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising tributes manager...");
        TributeManager.init();
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
        getServer().getPluginManager().registerEvents(new PlayerDisconnectionListener(), this);
        getLogger().info("Done");

        getLogger().info("Initialising commands...");
        getCommand("kits").setExecutor(new ListKitCommand());
        getCommand("kit").setExecutor(new SelectKitCommand());
        getCommand("change_state").setExecutor(new ChangeGameStateCommand());
        getCommand("gift").setExecutor(new GiftCommand());
        getCommand("set_popularity").setExecutor(new SetPopularityCommand());
        getCommand("popularity").setExecutor(new CheckPopularityCommand());
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
