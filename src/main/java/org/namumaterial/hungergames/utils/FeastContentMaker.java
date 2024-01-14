package org.namumaterial.hungergames.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FeastContentMaker {
    private final ItemStack[] LOOT = {
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

        new ItemStack(Material.GOLDEN_APPLE, 1),
        new ItemStack(Material.BOW, 1),
        new ItemStack(Material.CROSSBOW, 1),
        new ItemStack(Material.ARROW, 15),
        new ItemStack(Material.ARROW, 24),
        new ItemStack(Material.ARROW, 30),
        new ItemStack(Material.ARROW, 42),

        new ItemStack(Material.FIRE_CHARGE, 12),
        new ItemStack(Material.FIRE_CHARGE, 15),
        new ItemStack(Material.FIRE_CHARGE, 20),
        new ItemStack(Material.TNT, 10),
        new ItemStack(Material.TNT, 12),
        new ItemStack(Material.TNT, 17),
        new ItemStack(Material.SADDLE, 1),
    };

    public void makeFeastContent(Inventory inventory) {
        final int NUMBER_OF_ITEMSTACK_IN_GIFT = getRandomNumberOfItemStackInFeast();

        for (int iterator = 0; iterator < NUMBER_OF_ITEMSTACK_IN_GIFT; ++iterator) {
            inventory.addItem(pickRandomItemStack());
        }
    }

    private ItemStack pickRandomItemStack() {
        Random random = new Random();

        return this.LOOT[random.nextInt(LOOT.length)];
    }

    private int getRandomNumberOfItemStackInFeast() {
        Random random = new Random();

        return random.nextInt(HungerGamesConfiguration.MAX_ITEM_IN_FEAST);
    }
}
