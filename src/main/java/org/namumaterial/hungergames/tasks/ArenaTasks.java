package org.namumaterial.hungergames.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class ArenaTasks extends BukkitRunnable {

    @Override
    public void run() {
        reduceArena();
        runBorderAndPlayerVerificationTasks();
    }

    private void giveDamageToPlayer(Player player) {
        final double OUT_OF_ARENA_DAMAGE = 5.0;
        PlayerRawMessageSender.sendInformationMessage("You are outside of the arena. Come back quick are you will die !", player);
        player.damage(OUT_OF_ARENA_DAMAGE);
    }

    private void reduceArena() {
        if (!HungerGames.arena.isReductionFinished() && HungerGameStateManager.gameIsLaunched()) {
             final double ARENA_REDUCING_VALUE = 10.0;

             HungerGames.arena.reduceRadius(ARENA_REDUCING_VALUE);
        }
    }

    private void runBorderAndPlayerVerificationTasks() {
        if (HungerGameStateManager.gameIsLaunched()) {

            for (Player player: TributeManager.getAlivePlayers()) {
                if (!HungerGames.arena.isInsideRegion(player)) {
                    giveDamageToPlayer(player);
                } else if (HungerGames.arena.isNearBorder(player)) {
                    PlayerRawMessageSender.sendInformationMessage("You are near the arena border ! Turn around to avoid being killed !", player);
                }
            }
        }
    }

}
