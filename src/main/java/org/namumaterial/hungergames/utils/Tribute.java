package org.namumaterial.hungergames.utils;

import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;

public class Tribute {
    private Kit kit;
    private int popularity;

    public Tribute(Kit kit, int popularity) {
        this.kit = kit;
        this.popularity = popularity;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
