package org.namumaterial.hungergames.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GiftContentMaker {
    private ItemStack[] loot = {
        //Diamond stuff
        new ItemStack(Material.DIAMOND, 1),
        new ItemStack(Material.DIAMOND, 2),
        new ItemStack(Material.DIAMOND, 3),
        new ItemStack(Material.DIAMOND_SWORD, 1),
        new ItemStack(Material.DIAMOND_AXE, 1),
        new ItemStack(Material.DIAMOND_PICKAXE, 1),
        new ItemStack(Material.DIAMOND_HELMET, 1),
        new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
        new ItemStack(Material.DIAMOND_LEGGINGS, 1),
        new ItemStack(Material.DIAMOND_BOOTS, 1),
        //Iron
        new ItemStack(Material.IRON_INGOT, 5),
        new ItemStack(Material.IRON_SWORD, 1),
        new ItemStack(Material.IRON_AXE, 1),
        new ItemStack(Material.IRON_PICKAXE, 1),
        new ItemStack(Material.IRON_HELMET, 1),
        new ItemStack(Material.IRON_CHESTPLATE, 1),
        new ItemStack(Material.IRON_LEGGINGS, 1),
        new ItemStack(Material.IRON_BOOTS, 1),

        //Gold
        new ItemStack(Material.GOLD_INGOT, 3),
        new ItemStack(Material.GOLDEN_SWORD, 1),
        new ItemStack(Material.GOLDEN_AXE, 1),
        new ItemStack(Material.GOLDEN_PICKAXE, 1),
        new ItemStack(Material.GOLDEN_HELMET, 1),
        new ItemStack(Material.GOLDEN_CHESTPLATE, 1),
        new ItemStack(Material.GOLDEN_LEGGINGS, 1),
        new ItemStack(Material.GOLDEN_BOOTS, 1),

        //Food
        new ItemStack(Material.COOKED_BEEF, 3),
        new ItemStack(Material.COOKED_BEEF, 4),
        new ItemStack(Material.COOKED_BEEF, 5),
        new ItemStack(Material.GOLDEN_APPLE, 1),
        new ItemStack(Material.GOLDEN_APPLE, 2),
        new ItemStack(Material.GOLDEN_APPLE, 3),
        new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1),

        //Random items
        new ItemStack(Material.ENCHANTING_TABLE, 1),
        new ItemStack(Material.BUCKET, 1),
        new ItemStack(Material.BUCKET, 2),
        new ItemStack(Material.WATER_BUCKET, 1),
        new ItemStack(Material.LAVA_BUCKET, 1),
        new ItemStack(Material.BOW, 1),
        new ItemStack(Material.CROSSBOW, 1),
        new ItemStack(Material.ARROW, 15),
        new ItemStack(Material.ARROW, 24),
        new ItemStack(Material.ARROW, 30),
        new ItemStack(Material.ARROW, 42),
        new ItemStack(Material.ARROW, 64),
        new ItemStack(Material.ANVIL, 1),
    };

    public void makeGiftContent(Inventory giftInventory) {
        final int NUMBER_OF_ITEMSTACK_IN_GIFT = getRandomNumberOfItemStackInGift();

        for (int iterator = 0; iterator < NUMBER_OF_ITEMSTACK_IN_GIFT; ++iterator) {
            giftInventory.addItem(pickRandomItemStack());
        }
    }

    private ItemStack pickRandomItemStack() {
        Random random = new Random();

        return this.loot[random.nextInt(loot.length)];
    }

    private int getRandomNumberOfItemStackInGift() {
        final int MAX_ITEMSTACK_NUMBER_IN_GIFT = 10;
        Random random = new Random();

        return random.nextInt(MAX_ITEMSTACK_NUMBER_IN_GIFT);
    }
}
