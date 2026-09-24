package org.namumaterial.hungergames.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.namumaterial.hungergames.kits.*;

import java.util.*;

public class KitManager {
    private static Map<String, Kit> kits;

    public static void init() {
        List<Kit> kitsList = new ArrayList<>(Arrays.asList(
                new ButcherKit(),
                new CareerKit(),
                new FinnickKit(),
                new GaleKit(),
                new GhastKit(),
                new HorsemanKit(),
                new KatnissKit(),
                new MinerKit(),
                new PeetaKit(),
                new NoKit()
        ));

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

    public static void giveKitToPlayers() {
        for (Player player: TributeManager.getTributePlayers()) {
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
