package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.NoKit;
import org.namumaterial.hungergames.utils.Tribute;

import java.util.HashMap;
import java.util.Map;

// A tribute is a player taking part in the game and still alive.
// Players are registered when they enter the lobby, and removed when they die or disconnect.
public class TributeManager {
    public static Map<Player, Tribute> tributes;

    public static void init() {
        tributes = new HashMap<>();

        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            tributes.put(player, new Tribute(KitManager.getKitByName(NoKit.NAME), 0));
        }
    }

    public static void addPlayer(Player player) {
        tributes.put(player, new Tribute(KitManager.getKitByName(NoKit.NAME), 0));
    }

    public static void removePlayer(Player player) {
        tributes.remove(player);
    }

    public static void reset() {
        tributes.clear();
    }

    public static boolean isTribute(Player player) {
        return tributes.containsKey(player);
    }

    // Returns null if the player is not a tribute : check it with isTribute() first
    public static Tribute getTribute(Player player) {
        return tributes.get(player);
    }

}
