package org.namumaterial.hungergames;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.namumaterial.hungergames.commands.*;
import org.namumaterial.hungergames.listeners.*;
import org.namumaterial.hungergames.managers.*;
import org.namumaterial.hungergames.utils.Arena;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

public final class HungerGames extends JavaPlugin {
    public static Arena arena;

    @Override
    public void onEnable() {
        loadConfig();

        initItems();

        initKits();

        initTributeManager();

        initArena();

        initHungerGameStateManager();

        initEvents();

        initCommands();

        initTaskManager();
    }

    private void initItems() {
        getLogger().info("Initialising items...");
        ItemManager.createItems();
        getLogger().info("Done");
    }

    private void initTaskManager() {
        getLogger().info("Initialising tasks...");
        TaskManager.runTasks(this);
        getLogger().info("Done");
    }

    private void loadConfig() {
        getLogger().info("Loading config...");
        HungerGamesConfiguration.init(this);
        getLogger().info("Done");
    }

    private void initKits() {
        getLogger().info("Initialising kits...");
        KitManager.init();
        getLogger().info("Done");
    }

    private void initTributeManager() {
        getLogger().info("Initialising tributes manager...");
        TributeManager.init();
        getLogger().info("Done");
    }

    private void initArena() {
        getLogger().info("Initialising arena...");
        createArena();
        getLogger().info("Done");
    }

    private void initHungerGameStateManager() {
        getLogger().info("Initialising plugin's state manager...");
        HungerGameStateManager.init();
        getLogger().info("Done");
    }

    private void initEvents() {
        getLogger().info("Initialising listeners...");
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerCompassListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerKillListener(), this);
        pluginManager.registerEvents(new PlayerMineOreListener(), this);
        pluginManager.registerEvents(new ShieldCraftListener(), this);
        pluginManager.registerEvents(new PlayerConnectionListener(), this);
        pluginManager.registerEvents(new PlayerDisconnectionListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new NoInteractionOnNotStartedListener(), this);
        pluginManager.registerEvents(new TntListener(), this);
        pluginManager.registerEvents(new FireballListener(), this);
        pluginManager.registerEvents(new TamedHorseListener(), this);
        pluginManager.registerEvents(new KitSelectorRightClickListener(), this);
        pluginManager.registerEvents(new KitSelectorInventoryClickListener(), this);
        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new PlayerEatCakeListener(), this);
        getLogger().info("Done");
    }

    private void initCommands() {
        getLogger().info("Initialising commands...");
        getCommand("kits").setExecutor(new ListKitCommand());
        getCommand("kit").setExecutor(new SelectKitCommand());
        getCommand("setstate").setExecutor(new ChangeGameStateCommand());
        getCommand("state").setExecutor(new CheckGameStateCommand());
        getCommand("gift").setExecutor(new GiftCommand());
        getCommand("feast").setExecutor(new FeastCommand());
        getCommand("setpopularity").setExecutor(new SetPopularityCommand());
        getCommand("popularity").setExecutor(new CheckPopularityCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("arenaradius").setExecutor(new ArenaRadiusCommand());
        getCommand("kitselector").setExecutor(new KitSelectorCommand());
        getLogger().info("Done");
    }

    public void createArena() {
        arena = new Arena();
    }

    @Override
    public void onDisable() {
        getLogger().info("Hunger games plugin is disabled");
    }
}
