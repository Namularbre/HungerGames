package org.namumaterial.hungergames.utils;

import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;

import java.util.ArrayList;
import java.util.Collection;

public class Tribute {
    private Kit kit;
    private int popularity;
    private Collection<Player> teammates;

    public Tribute(Kit kit, int popularity) {
        this.kit = kit;
        this.popularity = popularity;
        this.teammates = new ArrayList<>();
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

    public void addPopularity(int value) {
        this.popularity += value;
    }

    public Collection<Player> getTeammates() {
        return teammates;
    }

    public void addTeammates(Player tribute) {
        this.teammates.add(tribute);
    }
}
