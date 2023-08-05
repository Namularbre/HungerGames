package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class KatnissKit extends Kit {
    public KatnissKit() {
        super("Katniss");

        addItemByMaterial(Material.BOW);
        addItemByMaterial(Material.SPECTRAL_ARROW, 20);
    }
}
