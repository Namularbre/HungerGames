package org.namumaterial.hungergames;

import org.bukkit.plugin.java.JavaPlugin;
import org.namumaterial.hungergames.items.ItemManager;
import org.namumaterial.hungergames.listeners.PlayerCompassListener;

public final class HungerGames extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Initialising items...");
        ItemManager.init();
        getLogger().info("Done");

        getLogger().info("Initialising listeners...");
        getServer().getPluginManager().registerEvents(new PlayerCompassListener(), this);
        getLogger().info("Done");
    }

    @Override
    public void onDisable() {
        getLogger().info("Hunger games plugin is disabled");
    }
}
