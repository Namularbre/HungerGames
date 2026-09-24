package org.namumaterial.hungergames.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

// Values are read from config.yml. A missing key uses the value of the config.yml embedded in the plugin.
// An invalid value throws an IllegalArgumentException : the plugin refuses to start instead of running with a broken setup.
public class HungerGamesConfiguration {

    public static int MINIMAL_AMOUNT_OF_PLAYER;
    //Time config
    public static int SECOND_BEFORE_STARTING_GAME;
    public static int SECOND_BEFORE_PVP;

    //Arena configuration
    public static double ARENA_START_RADIUS;
    public static double ARENA_END_RADIUS;
    public static double ARENA_REDUCING_VALUE;

    //Feast configuration
    public static int MAX_ITEM_IN_FEAST;
    public static int NUMBER_OF_FEAST;
    public static int SECONDS_BETWEEN_FEAST;

    //Popularity & gift configuration
    public static int MAX_ITEM_IN_GIFT;
    public static int COAL_ORE_MINING_POPULARITY;
    public static int IRON_ORE_MINING_POPULARITY;
    public static int DIAMOND_ORE_MINING_POPULARITY;
    public static int MONSTER_KILLING_POPULARITY;
    public static int ANIMAL_KILLING_POPULARITY;
    public static int PLAYER_KILLING_POPULARITY;

    private static final int UNLIMITED_NUMBER_OF_FEAST = -1;

    private static FileConfiguration configuration;

    public static void init(Plugin plugin) {
        plugin.saveDefaultConfig();
        configuration = plugin.getConfig();

        loadData();
    }

    private static void loadData() {
        MINIMAL_AMOUNT_OF_PLAYER = getInt("minimal_amount_of_player", 1);
        SECOND_BEFORE_STARTING_GAME = getInt("second_before_starting_game", 0);
        SECOND_BEFORE_PVP = getInt("second_before_pvp", 0);

        ARENA_START_RADIUS = getPositiveDouble("arena_start_radius");
        ARENA_END_RADIUS = getPositiveDouble("arena_end_radius");
        ARENA_REDUCING_VALUE = getPositiveDouble("arena_reducing_pvp");

        if (ARENA_END_RADIUS > ARENA_START_RADIUS) {
            throw new IllegalArgumentException("Invalid config: arena_end_radius (" + ARENA_END_RADIUS
                    + ") must be lower or equal to arena_start_radius (" + ARENA_START_RADIUS + ")");
        }

        MAX_ITEM_IN_FEAST = getInt("max_item_in_feast", 1);
        NUMBER_OF_FEAST = getInt("number_of_feast", UNLIMITED_NUMBER_OF_FEAST);
        SECONDS_BETWEEN_FEAST = getInt("seconds_between_feast", 1);

        MAX_ITEM_IN_GIFT = getInt("max_item_in_gift", 1);
        COAL_ORE_MINING_POPULARITY = getInt("coal_ore_mining_popularity", 0);
        IRON_ORE_MINING_POPULARITY = getInt("iron_ore_mining_popularity", 0);
        DIAMOND_ORE_MINING_POPULARITY = getInt("diamond_ore_mining_popularity", 0);
        MONSTER_KILLING_POPULARITY = getInt("monster_killing_popularity", 0);
        ANIMAL_KILLING_POPULARITY = getInt("animal_killing_popularity", 0);
        PLAYER_KILLING_POPULARITY = getInt("player_killing_popularity", 0);
    }

    private static int getInt(String key, int minimum) {
        Object value = configuration.get(key);

        if (!(value instanceof Integer)) {
            throw new IllegalArgumentException("Invalid config: " + key + " must be an integer, found: " + value);
        }

        final int INT_VALUE = (Integer) value;

        if (INT_VALUE < minimum) {
            throw new IllegalArgumentException("Invalid config: " + key + " must be at least " + minimum + ", found: " + INT_VALUE);
        }

        return INT_VALUE;
    }

    private static double getPositiveDouble(String key) {
        Object value = configuration.get(key);

        // Accepts both 2500 and 2500.0
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Invalid config: " + key + " must be a number, found: " + value);
        }

        final double DOUBLE_VALUE = ((Number) value).doubleValue();

        if (DOUBLE_VALUE <= 0) {
            throw new IllegalArgumentException("Invalid config: " + key + " must be greater than 0, found: " + DOUBLE_VALUE);
        }

        return DOUBLE_VALUE;
    }
}
