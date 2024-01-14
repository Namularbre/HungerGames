package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;
import org.namumaterial.hungergames.utils.Tribute;

public class KitSelectorInventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && event.getView().getTitle().equals("Choose your kit:") && HungerGameStateManager.gameIsNotStarted()) {
            event.setCancelled(true);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                handleKitSelection(player, clickedItem);
            }
        }
    }

    private void handleKitSelection(Player player, ItemStack selectedKit) {
        Tribute tribute = TributeManager.getTribute(player);
        String kitName = selectedKit.getItemMeta().getDisplayName();
        Kit kit = KitManager.getKitByName(kitName);

        if (kit != null) {
            tribute.setKit(kit);

            PlayerRawMessageSender.sendValidationMessage("Kit " + kitName + " selected.", player);
        } else {
            PlayerRawMessageSender.sendErrorMessage("Error while selecting the kit, you still have a random kit.", player);
        }
    }
}
