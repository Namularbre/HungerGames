package org.namumaterial.hungergames.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack kitSelector;

    public static void createItems() {
        createKitSelector();
    }

    private static void createKitSelector() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add("Right click to use.");
        meta.setDisplayName("Kit Selector");
        meta.setLore(lore);
        item.setItemMeta(meta);

        kitSelector = item;
    }
}
