package org.namumaterial.hungergames.kits;

import org.bukkit.Material;

public class MinerKit extends Kit {

    public MinerKit() {
        super("Miner");

        addItemByMaterial(Material.IRON_PICKAXE);
        addItemByMaterial(Material.TORCH, 64);
    }
}
