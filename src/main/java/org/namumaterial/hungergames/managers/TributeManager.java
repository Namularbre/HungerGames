package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class TributeManager {
    public static Collection tributes;

    public static void init() {
        tributes = new ArrayList<Player>();
    }

    public static void addPlayer(Player player) {
        tributes.add(player);
    }

    public static void removePlayer(Player player) {
        tributes.remove(player);
    }

    public static int getNumberOfAlivePlayer() {
        int numberOfAlivePlayer = 0;
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            if (!player.isDead()) {
                numberOfAlivePlayer++;
            }
        }

        return numberOfAlivePlayer;
    }

    public static Collection<Player> getTributes() {
        return new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
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

}
