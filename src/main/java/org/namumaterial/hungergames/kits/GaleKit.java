package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class GaleKit extends Kit {

    public static final String NAME = "Gale";

    public GaleKit() {
        super(NAME);

        addItemByMaterial(Material.TNT, 10);
        addItemByMaterial(Material.COBWEB);
    }
}
