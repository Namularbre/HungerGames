package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.utils.Tribute;

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

    public static Tribute getTribute(Player player) {
        return tributes.get(player);
    }

}
