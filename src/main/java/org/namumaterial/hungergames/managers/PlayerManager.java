package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
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
            if (player.getOpenInventory().getTitle().equals(ItemManager.KIT_SELECTOR_MENU_TITLE)) {
                player.closeInventory();
            }

            PlayerInventory inventory = player.getInventory();

            // Inventory.remove(ItemStack) only removes stacks of the exact same amount, so 2 stacked selectors would stay
            for (int slot = 0; slot < inventory.getSize(); slot++) {
                if (ItemManager.kitSelector.isSimilar(inventory.getItem(slot))) {
                    inventory.setItem(slot, null);
                }
            }

            if (ItemManager.kitSelector.isSimilar(player.getItemOnCursor())) {
                player.setItemOnCursor(null);
            }
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

    // The lobby keeps the saturation at 20 : without this, tributes would need to sprint ~800 blocks before getting hungry
    public static void resetTributesHungerToVanilla() {
        final int FULL_FOOD = 20;
        final float VANILLA_SPAWN_SATURATION = 5.0F;
        final float NO_EXHAUSTION = 0.0F;

        for (Player tribute: TributeManager.getTributePlayers()) {
            tribute.setFoodLevel(FULL_FOOD);
            tribute.setSaturation(VANILLA_SPAWN_SATURATION);
            tribute.setExhaustion(NO_EXHAUSTION);
        }
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
