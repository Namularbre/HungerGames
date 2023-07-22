package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.managers.TributeManager;

import java.util.Collection;

public class ArenaTasks extends BukkitRunnable {

    @Override
    public void run() {
        checkForPlayerOutsideTheArena();
        reduceArena();
    }

    private void checkForPlayerOutsideTheArena() {
        Collection<Player> alivePlayers = TributeManager.getPlayers();

        for (Player player: alivePlayers) {
            if (!player.isDead() && !HungerGames.arena.isInsideRegion(player)) {
                giveDamageToPlayer(player);
            }
        }
    }

    private void giveDamageToPlayer(Player player) {
        final double OUT_OF_ARENA_DAMAGE = 2.0;
        player.sendRawMessage(ChatColor.GOLD + "You are outside of the arena. Come back quick are you will die !");
        player.damage(OUT_OF_ARENA_DAMAGE);
    }

    private void reduceArena() {
        if (!HungerGames.arena.isReductionFinished()) {
             final double ARENA_REDUCING_VALUE = 1.0;

             HungerGames.arena.reduceRadius(ARENA_REDUCING_VALUE);
        }
    }
}
