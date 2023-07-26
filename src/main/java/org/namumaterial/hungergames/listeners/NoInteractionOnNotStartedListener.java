package org.namumaterial.hungergames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
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
}
