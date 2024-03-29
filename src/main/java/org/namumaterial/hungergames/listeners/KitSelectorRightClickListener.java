package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.ItemManager;
import org.namumaterial.hungergames.managers.KitManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KitSelectorRightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (playerAsKitSelectorInHand(player) && isRightClickAction(event)) {
            event.setCancelled(true);

            openKitSelectionInterface(player);
        }
    }

    private boolean playerAsKitSelectorInHand(Player player) {
        return player.getInventory().getItemInMainHand().isSimilar(ItemManager.kitSelector);
    }

    private boolean isRightClickAction(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR;
    }

    private void openKitSelectionInterface(Player player) {
        Collection<Kit> kits = KitManager.getKits();

        final int NUMBER_OF_KITS = kits.size();

        final int INVENTORY_SIZE = (int) Math.ceil((double) NUMBER_OF_KITS / 9) * 9;

        Inventory menu = Bukkit.createInventory(null, INVENTORY_SIZE, "Choose your kit:");

        for (Kit kit: kits) {
            ItemStack item = new ItemStack(Material.NAME_TAG);
            ItemMeta meta = item.getItemMeta();

            List<String> lore = new ArrayList<>();

            for (Map.Entry<ItemStack, Integer> kitdata: kit.getItems().entrySet()) {
                lore.add(kitdata.getKey().getType() + " x" + kitdata.getValue().toString());
            }
            meta.setDisplayName(kit.getName());
            meta.setLore(lore);
            item.setItemMeta(meta);

            menu.addItem(item);
        }

        player.openInventory(menu);
    }
}
