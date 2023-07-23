package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.utils.Tribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TributeManager {
    public static Map<Player, Tribute> tributes;

    public static void init() {
        tributes = new HashMap<>();

        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            tributes.put(player, new Tribute(KitManager.getRandomKit(), 0));
        }
    }

    public static void addPlayer(Player player) {
        tributes.put(player, new Tribute(KitManager.getRandomKit(), 0));
    }

    public static void removePlayer(Player player) {
        tributes.remove(player);
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

    public static Collection<Player> getAlivePlayers() {
        ArrayList<Player> alivePlayers = new ArrayList<>();
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            if (!player.isDead() && player.getGameMode() == GameMode.SURVIVAL) {
                alivePlayers.add(player);
            }
        }

        return alivePlayers;
    }

    public static int getNumberOfPlayer() {
        return Bukkit.getServer().getOnlinePlayers().size();
    }

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

    public static Tribute getTribute(Player player) {
        return tributes.get(player);
    }

}
