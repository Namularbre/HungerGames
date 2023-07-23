package org.namumaterial.hungergames.managers;

import org.namumaterial.hungergames.kits.ArcherKit;
import org.namumaterial.hungergames.kits.CookKit;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.kits.MinerKit;

import java.util.*;

public class KitManager {
    private static Map<String, Kit> kits;

    public static void init() {
        ArrayList<Kit> kitsList = new ArrayList<>(Arrays.asList(new ArcherKit(), new MinerKit(), new CookKit(), new ArcherKit()));

        kits = new HashMap<>();

        for (Kit kit: kitsList) {
            kits.put(kit.getName(), kit);
        }
    }

    public static Collection<Kit> getKits() {
        return kits.values();
    }

    public static Kit getKitByName(String name) {
        try {
            return kits.get(name);
        } catch (Exception exception) {
            return null;
        }
    }

    public static Kit getRandomKit() {
        Random random = new Random();

        final int KIT_INDEX = random.nextInt(kits.keySet().size());
        final ArrayList<String> KITS_LIST = new ArrayList<>(kits.keySet());

        return kits.get(KITS_LIST.get(KIT_INDEX));
    }
}
