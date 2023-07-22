package org.namumaterial.hungergames.managers;

import org.namumaterial.hungergames.kits.ArcherKit;
import org.namumaterial.hungergames.kits.CookKit;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.kits.MinerKit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class KitManager {
    private static Collection<Kit> kits;

    public static void init() {
        kits = new ArrayList<>(Arrays.asList(new ArcherKit(), new MinerKit(), new CookKit(), new ArcherKit()));
    }

    public static Collection<Kit> getKits() {
        return kits;
    }
}
