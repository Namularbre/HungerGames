package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class GaleKit extends Kit {
    public GaleKit() {
        super("Gale");

        addItemByMaterial(Material.TNT, 10);
        addItemByMaterial(Material.COBWEB);
    }
}
