package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class CareerKit extends Kit {

    public static final String NAME = "Career";

    public CareerKit() {
        super(NAME);

        addItemByMaterial(Material.STONE_SWORD);
        addItemByMaterial(Material.LEATHER_BOOTS);
    }
}
