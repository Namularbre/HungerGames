package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class FinnickKit extends Kit {

    public static final String NAME = "Finnick";

    public FinnickKit() {
        super(NAME);

        addItemByMaterial(Material.TRIDENT);
        addItemByMaterial(Material.COBWEB, 20);
    }
}
