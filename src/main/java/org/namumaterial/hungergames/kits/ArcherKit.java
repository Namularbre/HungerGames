package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class ArcherKit extends Kit {

    public ArcherKit() {
        super("Archer");

        addItemByMaterial(Material.BOW);
        addItemByMaterial(Material.ARROW, 10);
    }
}
