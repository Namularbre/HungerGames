package org.namumaterial.hungergames.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.namumaterial.hungergames.managers.TributeManager;

public class PlayerDisconnectionListener implements Listener {
    @EventHandler
    public void onPlayerDisconnection(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        TributeManager.removePlayer(player);
        event.setQuitMessage(ChatColor.GOLD + "Tribute " + player.getName() + " escaped the game");
    }
}
