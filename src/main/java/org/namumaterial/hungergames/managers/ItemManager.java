package org.namumaterial.hungergames.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static final String KIT_SELECTOR_LORE = "Right click to use.";
    public static final String KIT_SELECTOR_DISPLAY_NAME = "Kit Selector";
    public static ItemStack kitSelector;
    public static final String KIT_SELECTOR_MENU_TITLE = "Choose your kit:";

    public static void createItems() {
        createKitSelector();
    }

    private static void createKitSelector() {
        // Must not be a block : when right-clicking with a block, the client places it before the server cancels it,
        // which makes a ghost block and makes the item disappear from the client inventory
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(KIT_SELECTOR_LORE);
        meta.setDisplayName(KIT_SELECTOR_DISPLAY_NAME);
        meta.setLore(lore);
        item.setItemMeta(meta);

        kitSelector = item;
    }
}
