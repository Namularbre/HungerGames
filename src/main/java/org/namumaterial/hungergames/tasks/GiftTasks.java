package org.namumaterial.hungergames.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.GiftManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.Tribute;

public class GiftTasks extends BukkitRunnable {

    private final int giftPopularity = 1000;

    @Override
    public void run() {
        giveGiftToTributes();
    }

    private void giveGiftToTributes() {
        for (Player player : TributeManager.getTributePlayers()) {
            Tribute tribute = TributeManager.getTribute(player);

            if (asEnoughPopularity(tribute)) {
                tribute.setPopularity(tribute.getPopularity() - this.giftPopularity);
                GiftManager.sendGift(player);
            }
        }
    }

    private boolean asEnoughPopularity(Tribute tribute) {
        return tribute.getPopularity() >= this.giftPopularity;
    }
}
