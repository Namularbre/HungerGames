package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionListener implements Listener {
    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent event) {
        Player player = (Player) event.getPlayer();

        final String message = player.getDisplayName() + " volunteers as a tribute.";
        event.setJoinMessage(message);
    }
}
