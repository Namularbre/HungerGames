package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class GhastKit extends Kit {

    public GhastKit() {
        super("Ghast");

        addItemByMaterial(Material.FIRE_CHARGE, 20);
    }
}
