package org.namumaterial.hungergames.listeners;

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
            setJoinMessage(event, player);

            setPlayerNotStarted(player);
        } else {
            setPlayerSpectator(player);
        }
    }

    private static void setJoinMessage(PlayerJoinEvent event, Player player) {
        final String MESSAGE = player.getDisplayName() + " is volunteers as a tribute.";
        event.setJoinMessage(MESSAGE);
    }

    private void addPlayerToTributeManager(Player player) {
        TributeManager.addPlayer(player);
    }

    private void setPlayerNotStarted(Player player) {
        PlayerRawMessageSender.sendInformationMessage("Do /kits to see the kits, and then /kit [name] to select the kit.", player);

        addPlayerToTributeManager(player);
    }

    private void setPlayerSpectator(Player player) {
        PlayerRawMessageSender.sendInformationMessage("Game is already started, but you can have a look !", player);
        player.setGameMode(GameMode.SPECTATOR);
    }
}
