package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class ButcherKit extends Kit {
    public ButcherKit() {
        super("Butcher");

        addItemByMaterial(Material.GOLDEN_AXE);
        addItemByMaterial(Material.PORKCHOP, 2);
    }
}
