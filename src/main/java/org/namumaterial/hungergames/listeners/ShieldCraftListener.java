package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class ShieldCraftListener implements Listener {
    @EventHandler
    public void onPlayerCraftShield(CraftItemEvent event) {
        Material resultMaterial = event.getRecipe().getResult().getType();

        if (resultMaterial.equals(Material.SHIELD)) {
            event.setCancelled(true);
            Bukkit.getServer().broadcastMessage("Shield are not craftable");
        }
    }
}
