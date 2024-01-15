package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class PeetaKit extends Kit {
    public PeetaKit() {
        super("Peeta");

        addItemByMaterial(Material.CAKE, 1);
        addItemByMaterial(Material.BREAD, 3);
    }
}
