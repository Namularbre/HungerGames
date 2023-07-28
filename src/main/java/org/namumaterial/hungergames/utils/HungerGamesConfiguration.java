package org.namumaterial.hungergames.utils;

public class HungerGamesConfiguration {
    //Global config
    public static final int MINIMAL_AMOUNT_OF_PLAYER = 1;
    public static final int SECOND_BEFORE_STARTING_GAME_WHEN_ENOUGH_PLAYER = 90;
    public static final int SECOND_BEFORE_PVP = 90;

    //Arena configuration
    public static final double ARENA_START_RADIUS = 1000.0;
    public static final double ARENA_END_RADIUS = 200.0;
    public static final double ARENA_REDUCING_VALUE = 10.0;
    public static final int ARENA_REDUCING_PERIOD = 30;

    //Feast configuration
    public static final int MAX_ITEM_IN_FEAST = 15;

    //Popularity & gift configuration
    public static final int MAX_ITEM_IN_GIFT = 10;
    public static final int COAL_ORE_MINING_POPULARITY = 5;
    public static final int IRON_ORE_MINING_POPULARITY = 50;
    public static final int DIAMOND_ORE_MINING_POPULARITY = 250;
    public static final int MONSTER_KILLING_POPULARITY = 50;
    public static final int ANIMAL_KILLING_POPULARITY = 10;
    public static final int PLAYER_KILLING_POPULARITY = 500;

}
