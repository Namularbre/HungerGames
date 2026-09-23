package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class GhastKit extends Kit {

    public static final String NAME = "Ghast";

    public GhastKit() {
        super(NAME);

        addItemByMaterial(Material.FIRE_CHARGE, 20);
    }
}
