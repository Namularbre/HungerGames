package org.namumaterial.hungergames.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack poisonousSword;

    public static void init() {
        createPoisonousSword();
    }

    private static void createPoisonousSword() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add("Have a chance to give poison to your enemies");

        meta.setLore(lore);
        meta.setDisplayName("§2Poisonous sword");

        item.setItemMeta(meta);

        poisonousSword = item;
    }
}
