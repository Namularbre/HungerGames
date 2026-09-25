package org.namumaterial.hungergames.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;
import org.namumaterial.hungergames.utils.Tribute; //Don't remove

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
        final String MESSAGE = player.getDisplayName() + " volunteers as a tribute.";
        event.setJoinMessage(MESSAGE);
    }

    private void setPlayerNotStarted(Player player) {
        PlayerManager.setPlayerAsNotStartedState(player);
        PlayerRawMessageSender.sendInformationMessage("Do /kits to see the kits, then /kit [name] to select one, or use the kit selector.", player);
    }

    private void setPlayerSpectator(Player player) {
        PlayerRawMessageSender.sendInformationMessage("The game has already started, but you can watch!", player);
        player.setGameMode(GameMode.SPECTATOR);
    }
}
