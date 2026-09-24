package org.namumaterial.hungergames.managers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.utils.FeastContentMaker;

import java.util.Random;

public class FeastManager {

    public static void placeFeast() {
        Location feastLocation = getLocation();
        Block chestBlock = feastLocation.getBlock();
        chestBlock.setType(Material.CHEST);

        if (chestBlock.getState() instanceof Chest) {
            Chest chest = (Chest) chestBlock.getState();
            FeastContentMaker feastContentMaker = new FeastContentMaker();
            feastContentMaker.makeFeastContent(chest.getInventory());
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "A feast is generated at : " + feastLocationToString(feastLocation));
        }
    }

    private static Location getLocation() {
        World world = HungerGames.arena.getCenter().getWorld();

        Random random = new Random();
        // Half the width of the final border, which is a square
        final double HALF_SIZE = HungerGames.arena.getEndRadius();

        // The difference of two random numbers is between -1 and 1, but more often close to 0 :
        // feasts can spawn anywhere in the final arena, but tend to be close to the spawn
        double xOffset = (random.nextDouble() - random.nextDouble()) * HALF_SIZE;
        double zOffset = (random.nextDouble() - random.nextDouble()) * HALF_SIZE;

        double randomX = HungerGames.arena.getCenter().getX() + xOffset;
        double randomZ = HungerGames.arena.getCenter().getZ() + zOffset;

        int groundY = world.getHighestBlockYAt((int) randomX, (int) randomZ);

        return new Location(world, randomX, groundY + 1, randomZ);
    }

    private static String feastLocationToString(Location feastLocation) {
        return "x=" + feastLocation.getBlockX() + ",y=" + feastLocation.getBlockY() + ",z=" + feastLocation.getBlockZ();
    }
}
