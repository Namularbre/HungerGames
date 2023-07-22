package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class CookKit extends Kit{
    public CookKit() {
        super("Cook");

        addItemByMaterial(Material.FURNACE);
        addItemByMaterial(Material.COOKED_BEEF, 3);
    }
}
