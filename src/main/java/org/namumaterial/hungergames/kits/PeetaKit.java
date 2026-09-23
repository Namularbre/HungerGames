package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class PeetaKit extends Kit {

    public static final String NAME = "Peeta";

    public PeetaKit() {
        super(NAME);

        addItemByMaterial(Material.CAKE, 1);
        addItemByMaterial(Material.BREAD, 3);
    }
}
