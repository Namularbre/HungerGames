package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class FinnickKit extends Kit {
    public FinnickKit() {
        super("Finnick");

        addItemByMaterial(Material.TRIDENT);
        addItemByMaterial(Material.COBWEB, 20);
    }
}
