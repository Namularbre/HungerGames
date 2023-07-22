package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.namumaterial.hungergames.managers.TributeManager;

public class PlayerConnectionListener implements Listener {
    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        final String message = ChatColor.GOLD +  player.getDisplayName() + " is volunteers as a tribute.";
        event.setJoinMessage(message);

        final String playerHelpMessage = ChatColor.GOLD + "Do /kits to see the kits, and then /kit [name] to select the kit.";
        player.sendRawMessage(playerHelpMessage);

        changeHungerGameState();
    }

    private void changeHungerGameState() {
        final int NUMBER_OF_PLAYER = TributeManager.getNumberOfPlayer();
        final int MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START = 4;

        if (NUMBER_OF_PLAYER < MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + (MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START - NUMBER_OF_PLAYER) + " player needed before start.");
        } else {

        }
    }
}
