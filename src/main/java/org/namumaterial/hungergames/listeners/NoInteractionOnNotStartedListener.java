package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

public class NoInteractionOnNotStartedListener implements Listener {
    @EventHandler
    public void onEntityBreakBlock(BlockBreakEvent event) {
        if (HungerGameStateManager.gameIsNotStarted()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent event) {
        if (HungerGameStateManager.gameIsNotStarted()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerOpenBlockInterface(PlayerInteractEvent event) {
        Action action = event.getAction();
        Block clickedBlock = event.getClickedBlock();

        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            Material blockType = clickedBlock.getType();

            if (blockType == Material.CHEST || blockType == Material.TRAPPED_CHEST ||
                    blockType == Material.ENDER_CHEST || blockType == Material.FURNACE ||
                    blockType == Material.BLAST_FURNACE || blockType == Material.SMOKER ||
                    blockType == Material.CRAFTING_TABLE || blockType == Material.ENCHANTING_TABLE ||
                    blockType == Material.ANVIL || blockType == Material.CHIPPED_ANVIL ||
                    blockType == Material.DAMAGED_ANVIL || blockType == Material.BARREL ||
                    blockType == Material.DISPENSER || blockType == Material.DROPPER ||
                    blockType == Material.HOPPER || blockType == Material.BREWING_STAND ||
                    blockType == Material.LEVER || blockType == Material.STONE_BUTTON ||
                    blockType == Material.ACACIA_BUTTON || blockType == Material.BIRCH_BUTTON ||
                    blockType == Material.DARK_OAK_BUTTON || blockType == Material.JUNGLE_BUTTON ||
                    blockType == Material.OAK_BUTTON || blockType == Material.SPRUCE_BUTTON ||
                    blockType == Material.REPEATER || blockType == Material.COMPARATOR ||
                    blockType == Material.REDSTONE_WIRE || blockType == Material.TRIPWIRE ||
                    blockType == Material.BELL || blockType == Material.LOOM ||
                    blockType == Material.CARTOGRAPHY_TABLE || blockType == Material.FLETCHING_TABLE ||
                    blockType == Material.GRINDSTONE || blockType == Material.SMITHING_TABLE ||
                    blockType == Material.STONECUTTER || blockType == Material.LECTERN ||
                    blockType == Material.CAULDRON || blockType == Material.COMPOSTER) {

                event.setCancelled(true);
            }
        }
    }
}
