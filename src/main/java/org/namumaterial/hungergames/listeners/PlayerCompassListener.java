package org.namumaterial.hungergames.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.namumaterial.hungergames.managers.PlayerManager;

public class PlayerCompassListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (hasACompassInInventory(player)) {
            Player nearestPlayer = PlayerManager.getNearestPlayer(player);

            if (nearestPlayer != null) {
                Location nearestPlayerLocation = nearestPlayer.getLocation();
                player.setCompassTarget(nearestPlayerLocation);
            }
        }
    }

    private static boolean hasACompassInInventory(Player player) {
        return player.getInventory().contains(Material.COMPASS);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (hasCompassInHand(player) && isRightClickAction(event)) {
            Player nearestPlayer = PlayerManager.getNearestPlayer(player);

            if (nearestPlayer != null) {
                Location nearestPlayerLocation = nearestPlayer.getLocation();

                final double distanceBetweenPlayers = nearestPlayerLocation.distance(player.getLocation());

                String message = "PLayer " + nearestPlayer.getName() + " is at " + Math.floor(distanceBetweenPlayers) + " block(s) from you.";
                player.sendRawMessage(message);
            } else {
                player.sendRawMessage("No player where found.");
            }
        }
    }

    private static boolean hasCompassInHand(Player player) {
        return player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS);
    }

    private static boolean isRightClickAction(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
    }
}
