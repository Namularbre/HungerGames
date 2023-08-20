package org.namumaterial.hungergames.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class HungerGamesConfiguration {
    //Time config
    public static int MINIMAL_AMOUNT_OF_PLAYER = 1;
    public static int SECOND_BEFORE_STARTING_GAME = 90;
    public static int SECOND_BEFORE_PVP = 90;

    //Arena configuration
    public static double ARENA_START_RADIUS = 1000.0;
    public static double ARENA_END_RADIUS = 200.0;
    public static double ARENA_REDUCING_VALUE = 10.0;

    //Feast configuration
    public static int MAX_ITEM_IN_FEAST = 15;
    public static int NUMBER_OF_FEAST = 3;
    public static int SECONDS_BETWEEN_FEAST = 300;

    //Popularity & gift configuration
    public static int MAX_ITEM_IN_GIFT = 10;
    public static int COAL_ORE_MINING_POPULARITY = 5;
    public static int IRON_ORE_MINING_POPULARITY = 50;
    public static int DIAMOND_ORE_MINING_POPULARITY = 250;
    public static int MONSTER_KILLING_POPULARITY = 50;
    public static int ANIMAL_KILLING_POPULARITY = 10;
    public static int PLAYER_KILLING_POPULARITY = 500;

    private static FileConfiguration configuration;

    public static void init(Plugin plugin) {
        plugin.saveDefaultConfig();
        configuration = plugin.getConfig();

        loadData();
    }

    private static void loadData() {
        if (configuration != null) {
            MINIMAL_AMOUNT_OF_PLAYER = configuration.getInt("minimal_amount_of_player");
            SECOND_BEFORE_STARTING_GAME = configuration.getInt("second_before_starting_game");
            SECOND_BEFORE_PVP = configuration.getInt("second_before_pvp");

            ARENA_START_RADIUS = configuration.getDouble("arena_start_radius");
            ARENA_END_RADIUS = configuration.getDouble("arena_end_radius");
            ARENA_REDUCING_VALUE = configuration.getDouble("arena_reducing_pvp");

            MAX_ITEM_IN_FEAST = configuration.getInt("max_item_in_feast");
            NUMBER_OF_FEAST = configuration.getInt("number_of_feast");
            SECONDS_BETWEEN_FEAST = configuration.getInt("seconds_between_feast");

            MAX_ITEM_IN_GIFT = configuration.getInt("max_item_in_gift");
            COAL_ORE_MINING_POPULARITY = configuration.getInt("coal_ore_mining_popularity");
            IRON_ORE_MINING_POPULARITY = configuration.getInt("iron_ore_mining_popularity");
            DIAMOND_ORE_MINING_POPULARITY = configuration.getInt("diamond_ore_mining_popularity");
            MONSTER_KILLING_POPULARITY = configuration.getInt("monster_killing_popularity");
            ANIMAL_KILLING_POPULARITY = configuration.getInt("animal_killing_popularity");
            PLAYER_KILLING_POPULARITY = configuration.getInt("player_killing_popularity");
        }
    }

}
