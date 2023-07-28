package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;
import org.namumaterial.hungergames.utils.Tribute;

public class PlayerMineOreListener implements Listener {

    @EventHandler
    public void onPlayerMineOre(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (HungerGameStateManager.gameIsLaunched()) {
            if (block.getType() == Material.DIAMOND_ORE) {
                final int DIAMOND_MINING_POPULARITY_GAIN = HungerGamesConfiguration.DIAMOND_ORE_MINING_POPULARITY;

                Tribute tribute = TributeManager.getTribute(player);
                tribute.addPopularity(DIAMOND_MINING_POPULARITY_GAIN);
            } else if (block.getType() == Material.IRON_ORE) {
                final int IRON_MINING_POPULARITY_GAIN = HungerGamesConfiguration.IRON_ORE_MINING_POPULARITY;

                Tribute tribute = TributeManager.getTribute(player);
                tribute.addPopularity(IRON_MINING_POPULARITY_GAIN);
            } else if (block.getType() == Material.COAL_ORE) {
                final int COAL_MINING_POPULARITY_GAIN = HungerGamesConfiguration.COAL_ORE_MINING_POPULARITY;

                Tribute tribute = TributeManager.getTribute(player);
                tribute.addPopularity(COAL_MINING_POPULARITY_GAIN);
            }
        }
    }
}
