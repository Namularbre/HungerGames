package org.namumaterial.hungergames.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (gameIsNotStarted()) {
            cancelEvent(event);
        } else if (gameIsStarting()) {
            if (entityIsPlayer(entity)) {
                cancelEvent(event);
            }
        }
    }

    private static void cancelEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    private static boolean gameIsStarting() {
        return HungerGameStateManager.currentState == HungerGameStateManager.State.STARTING;
    }

    private static boolean gameIsNotStarted() {
        return HungerGameStateManager.currentState == HungerGameStateManager.State.NOT_STARTED;
    }

    private static boolean entityIsPlayer(Entity entity) {
        return entity instanceof Player;
    }


}
