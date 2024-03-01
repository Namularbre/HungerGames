package org.namumaterial.hungergames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

public class CreeperExplosionListener implements Listener {
    @EventHandler
    public void onCreeperExplode(ExplosionPrimeEvent event) {
        if (HungerGameStateManager.gameIsNotStarted()) {
            event.setCancelled(true);
        }
    }
}
