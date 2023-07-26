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
            event.setCancelled(true);
        }
    }
}
