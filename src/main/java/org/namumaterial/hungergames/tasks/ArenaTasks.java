package org.namumaterial.hungergames.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

import java.util.Collection;

public class ArenaTasks extends BukkitRunnable {

    @Override
    public void run() {
        checkForTributesOutsideTheArena();
        reduceArena();
    }

    private void checkForTributesOutsideTheArena() {
        Collection<Player> aliveTributes = TributeManager.getAlivePlayers();

        for (Player player: aliveTributes) {
            if (!HungerGames.arena.isInsideRegion(player)) {
                giveDamageToPlayer(player);
            }
        }
    }

    private void giveDamageToPlayer(Player player) {
        final double OUT_OF_ARENA_DAMAGE = 5.0;
        PlayerRawMessageSender.sendInformationMessage("You are outside of the arena. Come back quick are you will die !", player);
        player.damage(OUT_OF_ARENA_DAMAGE);
    }

    private void reduceArena() {
        if (!HungerGames.arena.isReductionFinished()) {
             final double ARENA_REDUCING_VALUE = 1.0;

             HungerGames.arena.reduceRadius(ARENA_REDUCING_VALUE);
        }
    }
}
