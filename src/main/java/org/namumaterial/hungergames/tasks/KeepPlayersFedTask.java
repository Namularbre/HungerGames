package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

// In the lobby, the players' food bar stays full
public class KeepPlayersFedTask extends BukkitRunnable {
    private static final int FULL_FOOD = 20;
    private static final float FULL_SATURATION = 20.0F;
    private static final float NO_EXHAUSTION = 0.0F;

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.setFoodLevel(FULL_FOOD);
            player.setSaturation(FULL_SATURATION);
            player.setExhaustion(NO_EXHAUSTION);
        }
    }
}
