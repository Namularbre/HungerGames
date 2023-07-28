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
        int radius = (int) HungerGames.arena.getEndRadius();
        double angle = random.nextDouble() * Math.PI * 2;
        double distance = random.nextDouble() * radius;

        double xOffset = distance * Math.cos(angle);
        double zOffset = distance * Math.sin(angle);

        double randomX = HungerGames.arena.getCenter().getX() + xOffset;
        double randomZ = HungerGames.arena.getCenter().getZ() + zOffset;

        int groundY = world.getHighestBlockYAt((int) randomX, (int) randomZ);

        return new Location(world, randomX, groundY, randomZ);
    }

    private static String feastLocationToString(Location feastLocation) {
        return "x=" + feastLocation.getBlockX() + ",y=" + feastLocation.getBlockY() + ",z=" + feastLocation.getBlockZ();
    }
}
