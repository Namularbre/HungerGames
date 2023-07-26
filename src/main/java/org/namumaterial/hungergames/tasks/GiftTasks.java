package org.namumaterial.hungergames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.GiftContentMaker;
import org.namumaterial.hungergames.utils.Tribute;

import java.util.Collection;

public class GiftTasks extends BukkitRunnable {
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
        if (!gameIsLaunched()) {
            return;
        }

        final int GIFT_POPULARITY = 1000;

        for (Player player : TributeManager.getAlivePlayers()) {
            Tribute tribute = TributeManager.getTribute(player);

            Bukkit.getServer().broadcastMessage("Give gifts task" + tribute.getPopularity() + " " + player.getName());

            if (tribute.getPopularity() >= GIFT_POPULARITY) {
                tribute.setPopularity(tribute.getPopularity() - GIFT_POPULARITY);
                GiftContentMaker giftContentMaker = new GiftContentMaker();

                giftContentMaker.makeGiftContent(player.getInventory());
                advertPlayer(player);
            }
        }
    }

    private boolean gameIsLaunched() {
        return HungerGameStateManager.currentState == HungerGameStateManager.State.PLAYING || HungerGameStateManager.currentState == HungerGameStateManager.State.STARTING;
    }

    private void advertPlayer(Player player) {
        player.sendRawMessage(ChatColor.GOLD + "You received a gift from sponsors !");
    }
}
