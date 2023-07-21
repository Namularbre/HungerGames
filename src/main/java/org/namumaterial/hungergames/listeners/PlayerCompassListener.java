package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerCompassListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().contains(Material.COMPASS)) {
            Player nearestPlayer = getNearestPlayer(player);

            if (nearestPlayer != null) {
                Location nearestPlayerLocation = nearestPlayer.getLocation();
                player.setCompassTarget(nearestPlayerLocation);
            }
        }
    }

    private Player getNearestPlayer(Player player) {
        Player nearestPlayer = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer != player) {
                double distance = onlinePlayer.getLocation().distance(player.getLocation());
                if (distance < nearestDistance) {
                    nearestPlayer = onlinePlayer;
                    nearestDistance = distance;
                }
            }
        }

        return nearestPlayer;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (hasCompassInHand(player) && isRightClickAction(event)) {
            Player nearestPlayer = getNearestPlayer(player);
            Location nearestPlayerLocation = nearestPlayer.getLocation();

            final double distanceBetweenPlayers = nearestPlayerLocation.distance(player.getLocation());

            String message = "PLayer " + nearestPlayer.getName() + " is at " + distanceBetweenPlayers + " block(s) from you.";
            player.sendRawMessage(message);
        }
    }

    private static boolean hasCompassInHand(Player player) {
        return player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS);
    }

    private static boolean isRightClickAction(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
    }
}
