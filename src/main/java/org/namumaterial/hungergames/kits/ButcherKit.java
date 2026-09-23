package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class ButcherKit extends Kit {

    public static final String NAME = "Butcher";

    public ButcherKit() {
        super(NAME);

        addItemByMaterial(Material.GOLDEN_AXE);
        addItemByMaterial(Material.PORKCHOP, 2);
    }
}
