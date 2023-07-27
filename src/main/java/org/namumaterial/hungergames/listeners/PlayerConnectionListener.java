package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class PlayerConnectionListener implements Listener {
    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (HungerGameStateManager.gameIsNotStarted()) {
            final String message = ChatColor.GOLD +  player.getDisplayName() + " is volunteers as a tribute.";
            event.setJoinMessage(message);

            setPlayerNotStarted(player);
        } else {
            setPlayerSpectator(player);
        }
    }

    private void changeHungerGameState() {
        final int NUMBER_OF_PLAYER = TributeManager.getNumberOfPlayer();
        final int MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START = 4;

        if (NUMBER_OF_PLAYER < MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + (MINIMAL_AMOUNT_OF_PLAYER_BEFORE_START - NUMBER_OF_PLAYER) + " player needed before start.");
        }
    }

    private void addPlayerToTributeManager(Player player) {
        TributeManager.addPlayer(player);
    }

    private void setPlayerNotStarted(Player player) {
        PlayerRawMessageSender.sendInformationMessage("Do /kits to see the kits, and then /kit [name] to select the kit.", player);

        addPlayerToTributeManager(player);
        changeHungerGameState();
    }

    private void setPlayerSpectator(Player player) {
        PlayerRawMessageSender.sendInformationMessage("Game is already started, but you can have a look !", player);
        player.setGameMode(GameMode.SPECTATOR);
        player.setCanPickupItems(false);
        player.setAllowFlight(true);
        player.setCollidable(false);
    }
}
