package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;

public class PlayerManager {

    public static Player getNearestPlayer(Player player) {
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

    public static void teleportAllPlayersToSpawn() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.teleport(HungerGames.arena.getCenter());
        }
    }

    public static int getNumberOfAlivePlayer() {
        int numberOfAlivePlayer = 0;
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            if (isPlaying(player)) {
                numberOfAlivePlayer++;
            }
        }

        return numberOfAlivePlayer;
    }

    private static boolean isPlaying(Player player) {
        return !player.isDead() && player.getGameMode() == GameMode.SURVIVAL;
    }
}
