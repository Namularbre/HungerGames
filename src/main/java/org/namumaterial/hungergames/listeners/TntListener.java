package org.namumaterial.hungergames.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

public class TntListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlock();

        if (placedBlock.getType() == Material.TNT) {
            Location TNTLocation = placedBlock.getLocation();

            convertTNTBlockLocationIntoEntityLocation(TNTLocation);
            removeTNTBlock(placedBlock);

            spawnTNTPrimedEntity(placedBlock, TNTLocation);
        }
    }

    private static void spawnTNTPrimedEntity(Block placedBlock, Location TNTLocation) {
        final int fuseTicks = SecondToTicksConverter.convert(HungerGamesConfiguration.TNT_FUSE_TIME_SECONDS);

        TNTPrimed tntPrimed = (TNTPrimed) placedBlock.getWorld().spawnEntity(TNTLocation, EntityType.PRIMED_TNT);
        tntPrimed.setFuseTicks(fuseTicks);
    }

    private static void convertTNTBlockLocationIntoEntityLocation(Location TNTLocation) {
        final double xAndZAxisReadjustment = 0.5;
        final double yNotReadjusted = 0.0;

        TNTLocation.add(xAndZAxisReadjustment, yNotReadjusted, xAndZAxisReadjustment);
    }

    private static void removeTNTBlock(Block placedBlock) {
        placedBlock.setType(Material.AIR);
    }
}
