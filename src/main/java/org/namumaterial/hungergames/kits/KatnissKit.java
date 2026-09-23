package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class KatnissKit extends Kit {

    public static final String NAME = "Katniss";

    public KatnissKit() {
        super(NAME);

        addItemByMaterial(Material.BOW);
        addItemByMaterial(Material.SPECTRAL_ARROW, 20);
    }
}
