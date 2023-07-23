package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

import java.util.Random;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        makeCanonBallSound(player);

        TributeManager.removePlayer(player);

        final int NUMBER_OF_PLAYER_REMAINING = TributeManager.getNumberOfAlivePlayer();
        final int ONE_PLAYER_LEFT = 1;

        if (NUMBER_OF_PLAYER_REMAINING != ONE_PLAYER_LEFT) {
            Bukkit.getServer().broadcastMessage(NUMBER_OF_PLAYER_REMAINING + "remaining !");
        } else {
            HungerGameStateManager.setCurrentStep(HungerGameStateManager.State.ENDED);
            Bukkit.getServer().broadcastMessage("You WON");
            makeWinAnimation(player);
        }
    }

    private void makeCanonBallSound(Player player) {
        final float VOLUME = 5.0f;
        final float PITCH = 1.0f;

        Location location = player.getLocation();
        location.getWorld().playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, VOLUME, PITCH);
    }

    private Sound pickRandomMusic() {
        Sound[] sounds = {Sound.MUSIC_DISC_CHIRP, Sound.MUSIC_DISC_BLOCKS, Sound.MUSIC_DISC_CAT, Sound.MUSIC_DISC_FAR, Sound.MUSIC_DISC_MALL};
        Random randomGenerator = new Random(sounds.length);
        final int musicIndex = randomGenerator.nextInt();

        return sounds[musicIndex];
    }

    private void makeWinAnimation(Player player) {
        final float VOLUME = 5.0f;
        final float PITCH = 1.0f;

        Location location = player.getLocation();
        location.getWorld().playSound(location, pickRandomMusic(), VOLUME, PITCH);
    }
}
