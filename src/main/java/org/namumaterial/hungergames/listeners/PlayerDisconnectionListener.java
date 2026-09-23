package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.managers.TributeManager;

public class PlayerDisconnectionListener implements Listener {
    @EventHandler
    public void onPlayerDisconnection(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (isPlaying(player)) {
            TributeManager.removePlayer(player);
        }

        event.setQuitMessage(ChatColor.GOLD + "Tribute " + player.getName() + " escaped the game");

        if (HungerGameStateManager.gameIsLaunched()) {
            int numberOfAlivePlayer = PlayerManager.getNumberOfAlivePlayer();
            final int ONE_PLAYER_LEFT = 1;

            if (isPlaying(player))
                numberOfAlivePlayer--;

            if (numberOfAlivePlayer == ONE_PLAYER_LEFT) {
                HungerGameStateManager.setEnded();
                Bukkit.getServer().broadcastMessage( ChatColor.GOLD + "GAME OVER");
            }
        }
    }

    private static boolean isPlaying(Player player) {
        return !player.isDead() && player.getGameMode() == GameMode.SURVIVAL;
    }
}
