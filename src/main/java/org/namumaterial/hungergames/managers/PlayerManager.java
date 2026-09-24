package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;

public class PlayerManager {

    public static Player getNearestPlayer(Player player) {
        Player nearestPlayer = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Player tribute : TributeManager.getTributePlayers()) {
            if (tribute != player) {
                double distance = tribute.getLocation().distance(player.getLocation());
                if (distance < nearestDistance) {
                    nearestPlayer = tribute;
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

    public static void removeKitSelectorFromInventory() {
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            player.getInventory().remove(ItemManager.kitSelector);
        }
    }

    public static void healPlayer(Player player) {
        final double FULL_HEALTH = 20.0;
        final int FULL_FOOD = 20;
        final float SATURATION = 20.0F;

        player.setHealth(FULL_HEALTH);
        player.setFoodLevel(FULL_FOOD);
        player.setSaturation(SATURATION);
    }

    public static void setPlayersAsNotStartedState() {
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            setPlayerAsNotStartedState(player);
        }
    }

    // Puts the player in the lobby and registers them as a tribute.
    // Players in creative or adventure mode (admins) are left untouched and don't play.
    public static void setPlayerAsNotStartedState(Player player) {
        if (player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.SURVIVAL) {
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
            player.getInventory().addItem(ItemManager.kitSelector);
            player.setExp(0.0F);
            healPlayer(player);
            TributeManager.addPlayer(player);
        }
    }
}
