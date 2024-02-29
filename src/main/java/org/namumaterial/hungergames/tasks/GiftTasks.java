package org.namumaterial.hungergames.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.GiftContentMaker;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;
import org.namumaterial.hungergames.utils.Tribute;

public class GiftTasks extends BukkitRunnable {

    private final int giftPopularity = 1000;

    @Override
    public void run() {
        giveGiftToTributes();
    }

    //This method is used with the command
    public void giveGiftToPlayer(Player player) {
        GiftContentMaker giftContentMaker = new GiftContentMaker();

        giftContentMaker.makeGiftContent(player.getInventory());
        player.updateInventory();
        advertPlayer(player);
    }

    private void giveGiftToTributes() {
        if (!HungerGameStateManager.gameIsLaunched()) {
            return;
        }

        for (Player player : PlayerManager.getAlivePlayers()) {
            Tribute tribute = TributeManager.getTribute(player);

            if (asEnoughPopularity(tribute)) {
                tribute.setPopularity(tribute.getPopularity() - this.giftPopularity);
                GiftContentMaker giftContentMaker = new GiftContentMaker();

                giftContentMaker.makeGiftContent(player.getInventory());
                advertPlayer(player);
            }
        }
    }

    private boolean asEnoughPopularity(Tribute tribute) {
        return tribute.getPopularity() >= this.giftPopularity;
    }

    private void advertPlayer(Player player) {
        PlayerRawMessageSender.sendInformationMessage("You received a gift from sponsors !", player);
    }
}
