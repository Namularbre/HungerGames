package org.namumaterial.hungergames.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.Tribute;

import java.util.Random;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killedPlayer = event.getEntity();

        makeCanonBallSound(killedPlayer);
        putDeadPlayerInSpectatorGamemode(killedPlayer);
        TributeManager.removePlayer(killedPlayer);
        givePopularityToKiller(killedPlayer);

        if (!HungerGameStateManager.checkForWinner()) {
            final int NUMBER_OF_PLAYER_REMAINING = TributeManager.getNumberOfTributes();
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + NUMBER_OF_PLAYER_REMAINING + " tributes remaining !");
        }
    }

    private void givePopularityToKiller(Player killedPlayer) {
        Player killer = killedPlayer.getKiller();

        if (killer != null && TributeManager.isTribute(killer)) {
            Tribute tribute = TributeManager.getTribute(killer);
            tribute.addPopularity(HungerGamesConfiguration.PLAYER_KILLING_POPULARITY);
        }
    }

    private void putDeadPlayerInSpectatorGamemode(Player killedPlayer) {
        killedPlayer.setGameMode(GameMode.SPECTATOR);
    }

    private void makeCanonBallSound(Player killedPlayer) {
        final float VOLUME = 5.0f;
        final float PITCH = 1.0f;

        Location location = killedPlayer.getLocation();
        location.getWorld().playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, VOLUME, PITCH);
    }
}
