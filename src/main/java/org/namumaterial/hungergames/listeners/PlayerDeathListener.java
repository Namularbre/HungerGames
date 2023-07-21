package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.managers.PluginStateManager;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        spawnLightning(player);

        final int NUMBER_OF_PLAYER_REMAINING = PlayerManager.getNumberOfAlivePlayer();
        final int ONE_PLAYER_LEFT = 1;

        if (NUMBER_OF_PLAYER_REMAINING != ONE_PLAYER_LEFT) {
            Bukkit.getServer().broadcastMessage(NUMBER_OF_PLAYER_REMAINING + "remaining !");
        } else {
            PluginStateManager.setCurrentStep(PluginStateManager.State.END);
            Bukkit.getServer().broadcastMessage("You WON");
        }
    }

    private void spawnLightning(Player player) {
        Location playerLocation = player.getLocation();
        player.getWorld().spawnEntity(playerLocation, EntityType.LIGHTNING);
    }
}
