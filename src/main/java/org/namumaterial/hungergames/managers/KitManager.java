package org.namumaterial.hungergames.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    public static void giveKitToPlayers() {
        for (Player player: TributeManager.getAlivePlayers()) {
            Kit selectedKit = TributeManager.getTribute(player).getKit();

            for (Map.Entry<ItemStack, Integer> itemAndAmount: selectedKit.getItems().entrySet()) {
                ItemStack itemStack = itemAndAmount.getKey();
                int amount = itemAndAmount.getValue();

                player.getInventory().addItem(new ItemStack(itemStack.getType(), amount));
                player.updateInventory();
            }
        }
    }
}
