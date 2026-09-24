package org.namumaterial.hungergames.managers;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.namumaterial.hungergames.HungerGames;

// In the lobby, time and weather are frozen : the game always starts in the morning, with a clear sky.
public class WorldManager {
    private static final long MORNING = 1000;

    public static void freezeWorld() {
        World world = getWorld();

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setTime(MORNING);
        world.setStorm(false);
        world.setThundering(false);
    }

    public static void unfreezeWorld() {
        World world = getWorld();

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, true);
    }

    private static World getWorld() {
        return HungerGames.arena.getCenter().getWorld();
    }
}
