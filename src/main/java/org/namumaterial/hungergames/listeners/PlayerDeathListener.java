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

        TributeManager.removePlayer(killedPlayer);

        final int NUMBER_OF_PLAYER_REMAINING = TributeManager.getNumberOfAlivePlayer();
        final int ONE_PLAYER_LEFT = 1;

        if (NUMBER_OF_PLAYER_REMAINING != ONE_PLAYER_LEFT) {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + NUMBER_OF_PLAYER_REMAINING + " tributes remaining !");
            givePopularityToKiller(killedPlayer);

        } else {
            HungerGameStateManager.setEnded();
            Bukkit.getServer().broadcastMessage( ChatColor.GOLD + "GAME OVER");
            makeWinAnimation(killedPlayer);
        }

        putDeadPlayerInSpectatorGamemode(killedPlayer);
    }

    private void givePopularityToKiller(Player killedPlayer) {
        Player killer = killedPlayer.getKiller();

        if (killer != null) {
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

    private Sound pickRandomMusic() {
        Sound[] sounds = {Sound.MUSIC_DISC_CHIRP, Sound.MUSIC_DISC_BLOCKS, Sound.MUSIC_DISC_CAT, Sound.MUSIC_DISC_FAR, Sound.MUSIC_DISC_MALL};
        Random randomGenerator = new Random(sounds.length);
        final int musicIndex = randomGenerator.nextInt();

        return sounds[musicIndex];
    }

    private void makeWinAnimation(Player killedPlayer) {
        final float VOLUME = 5.0f;
        final float PITCH = 1.0f;

        Location location = killedPlayer.getLocation();
        location.getWorld().playSound(location, pickRandomMusic(), VOLUME, PITCH);
    }
}
