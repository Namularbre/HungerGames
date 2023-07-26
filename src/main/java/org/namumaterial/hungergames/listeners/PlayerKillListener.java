package org.namumaterial.hungergames.listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.Tribute;

public class PlayerKillListener implements Listener {
    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {
        if (HungerGameStateManager.gameIsLaunched()) {
            LivingEntity killedEntity = event.getEntity();

            Player killer = killedEntity.getKiller();

            if (killer != null) {
                Tribute tribute = TributeManager.getTribute(killer);

                if (killedEntity instanceof Monster) {
                    final int POPULARITY_GAIN_FOR_MONSTER = 50;
                    tribute.addPopularity(POPULARITY_GAIN_FOR_MONSTER);
                } else if (killedEntity instanceof Animals) {
                    final int POPULARITY_GAIN_FOR_ANIMALS = 10;
                    tribute.addPopularity(POPULARITY_GAIN_FOR_ANIMALS);
                }
            }
        }
    }

}
