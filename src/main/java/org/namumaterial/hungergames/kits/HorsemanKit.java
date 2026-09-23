package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class HorsemanKit extends Kit {

    public static final String NAME = "Horseman";

    public HorsemanKit() {
        super(NAME);

        addItemByMaterial(Material.SADDLE);
        addItemByMaterial(Material.GOLDEN_SWORD);
    }
}
