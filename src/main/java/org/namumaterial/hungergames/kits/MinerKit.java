package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class MinerKit extends Kit {

    public static final String NAME = "Miner";

    public MinerKit() {
        super(NAME);

        addItemByMaterial(Material.IRON_PICKAXE);
        addItemByMaterial(Material.TORCH, 64);
    }
}
