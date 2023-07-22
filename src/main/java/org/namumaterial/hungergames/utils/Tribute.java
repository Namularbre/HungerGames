package org.namumaterial.hungergames.utils;

import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;

public class Tribute {
    private Player data;
    private Kit kit;
    private int popularity;

    public Tribute(Player data, Kit kit, int popularity) {
        this.data = data;
        this.kit = kit;
        this.popularity = popularity;
    }

    public Player getData() {
        return data;
    }

    public void setData(Player data) {
        this.data = data;
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
