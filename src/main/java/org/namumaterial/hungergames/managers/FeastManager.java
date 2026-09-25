package org.namumaterial.hungergames.managers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "A feast spawned at: " + feastLocationToString(feastLocation));
        }
    }

    // Retries a few times to avoid spawning a feast on water or lava
    private static Location getLocation() {
        final int MAX_ATTEMPTS = 20;

        Location location = getRandomGroundLocation();

        for (int attempt = 1; attempt < MAX_ATTEMPTS && isAboveLiquid(location); attempt++) {
            location = getRandomGroundLocation();
        }

        return location;
    }

    private static boolean isAboveLiquid(Location location) {
        return location.getBlock().getRelative(BlockFace.DOWN).isLiquid();
    }

    private static Location getRandomGroundLocation() {
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

        // Leaves are ignored, so the feast doesn't spawn on the top of a tree
        Block ground = world.getHighestBlockAt((int) Math.floor(randomX), (int) Math.floor(randomZ), HeightMap.MOTION_BLOCKING_NO_LEAVES);

        // Tree trunks are not the ground either
        while (Tag.LOGS.isTagged(ground.getType()) && ground.getY() > world.getMinHeight()) {
            ground = ground.getRelative(BlockFace.DOWN);
        }

        return ground.getRelative(BlockFace.UP).getLocation();
    }

    private static String feastLocationToString(Location feastLocation) {
        return "x=" + feastLocation.getBlockX() + ",y=" + feastLocation.getBlockY() + ",z=" + feastLocation.getBlockZ();
    }
}
