package org.namumaterial.hungergames;

import org.bukkit.plugin.java.JavaPlugin;
import org.namumaterial.hungergames.listeners.PlayerConnectionListener;
import org.namumaterial.hungergames.listeners.PlayerDeathListener;
import org.namumaterial.hungergames.listeners.ShieldCraftListener;
import org.namumaterial.hungergames.managers.ItemManager;
import org.namumaterial.hungergames.listeners.PlayerCompassListener;
import org.namumaterial.hungergames.managers.PluginStateManager;

public final class HungerGames extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Initialising items...");
        ItemManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising plugin's state manager");
        PluginStateManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising listeners...");
        getServer().getPluginManager().registerEvents(new PlayerCompassListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldCraftListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getLogger().info("Done");
    }

    @Override
    public void onDisable() {
        getLogger().info("Hunger games plugin is disabled");
    }
}
