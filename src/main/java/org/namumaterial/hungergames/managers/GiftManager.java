package org.namumaterial.hungergames.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.namumaterial.hungergames.utils.GiftContentMaker;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Like in the books, sponsors send their gifts with a parachute : a chest lands next to the tribute.
public class GiftManager {

    public static void sendGift(Player player) {
        Block parachuteBlock = findParachuteLandingBlock(player);

        if (parachuteBlock != null) {
            landParachute(player, parachuteBlock);
        } else {
            giveGiftInInventory(player);
        }
    }

    private static void landParachute(Player player, Block parachuteBlock) {
        parachuteBlock.setType(Material.CHEST);

        Chest chest = (Chest) parachuteBlock.getState();
        new GiftContentMaker().makeGiftContent(chest.getInventory());

        final int PARTICLES_COUNT = 30;
        final double PARTICLES_SPREAD = 0.4D;
        final double PARTICLES_SPEED = 0.02D;

        parachuteBlock.getWorld().spawnParticle(Particle.CLOUD, parachuteBlock.getLocation().add(0.5D, 1.0D, 0.5D),
                PARTICLES_COUNT, PARTICLES_SPREAD, PARTICLES_SPREAD, PARTICLES_SPREAD, PARTICLES_SPEED);
        // Only the receiver hears it
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);

        PlayerRawMessageSender.sendValidationMessage("A sponsor sent you a gift! Its parachute landed next to you.", player);
    }

    // No room for a chest around the player (in a tunnel for example) : the gift goes in the inventory, the rest drops
    private static void giveGiftInInventory(Player player) {
        final int CHEST_SIZE = 27;

        Inventory giftContent = Bukkit.createInventory(null, CHEST_SIZE);
        new GiftContentMaker().makeGiftContent(giftContent);

        for (ItemStack item : giftContent.getContents()) {
            if (item == null) {
                continue;
            }

            for (ItemStack leftover : player.getInventory().addItem(item).values()) {
                player.getWorld().dropItemNaturally(player.getLocation(), leftover);
            }
        }

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
        PlayerRawMessageSender.sendValidationMessage("A sponsor sent you a gift!", player);
    }

    // A free block around the player, standing on solid ground. Returns null if there is none.
    private static Block findParachuteLandingBlock(Player player) {
        final int HORIZONTAL_RANGE = 2;
        final int VERTICAL_RANGE = 1;

        Block feet = player.getLocation().getBlock();
        List<Block> candidates = new ArrayList<>();

        for (int dx = -HORIZONTAL_RANGE; dx <= HORIZONTAL_RANGE; dx++) {
            for (int dz = -HORIZONTAL_RANGE; dz <= HORIZONTAL_RANGE; dz++) {
                // Not on the player
                if (dx == 0 && dz == 0) {
                    continue;
                }

                for (int dy = -VERTICAL_RANGE; dy <= VERTICAL_RANGE; dy++) {
                    candidates.add(feet.getRelative(dx, dy, dz));
                }
            }
        }

        // Not always on the same side of the player
        Collections.shuffle(candidates);

        for (Block candidate : candidates) {
            if (canLandOn(candidate)) {
                return candidate;
            }
        }

        return null;
    }

    private static boolean canLandOn(Block block) {
        Block below = block.getRelative(BlockFace.DOWN);

        return isReplaceable(block) && below.getType().isSolid();
    }

    // Air, or plants like grass and flowers. Other blocks (torches, cobwebs...) may have been placed by a player.
    private static boolean isReplaceable(Block block) {
        Material type = block.getType();

        return block.isEmpty() || Tag.REPLACEABLE_PLANTS.isTagged(type) || Tag.SMALL_FLOWERS.isTagged(type);
    }
}
