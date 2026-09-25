package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

public class ShieldCraftListener implements Listener {
    // Fired when the crafting grid changes : the shield never shows up in the result slot
    @EventHandler
    public void onPrepareShieldCraft(PrepareItemCraftEvent event) {
        Recipe recipe = event.getRecipe();

        // No recipe matches the items in the grid
        if (recipe == null) {
            return;
        }

        if (recipe.getResult().getType() == Material.SHIELD) {
            event.getInventory().setResult(null);
        }
    }
}
