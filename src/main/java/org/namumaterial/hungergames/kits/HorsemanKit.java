package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class HorsemanKit extends Kit {
    public HorsemanKit() {
        super("Horseman");

        addItemByMaterial(Material.SADDLE);
        addItemByMaterial(Material.GOLDEN_SWORD);
    }
}
