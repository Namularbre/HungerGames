package org.namumaterial.hungergames.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Kit {
    private String name;
    private Map<ItemStack, Integer> items;

    public Kit(String name) {
        this.name = name;
        this.items = new HashMap<>();

        addItemByMaterial(Material.COMPASS);
    }

    public Map<ItemStack, Integer> getItems() {
        return this.items;
    }

    public void addItemByMaterial(Material material, int amount) {
        this.items.put(new ItemStack(material), amount);
    }

    public void addItemByMaterial(Material material) {
        this.items.put(new ItemStack(material), 1);
    }

    public void addItemsByItemStack(ItemStack itemStack, int amount) {
        this.items.put(itemStack, amount);
    }

    public void addItemByItemStack(ItemStack itemStack) {
        this.items.put(itemStack, 1);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String itemsToString = "";

        for (ItemStack itemStack: items.keySet()) {

            if (itemStack.hasItemMeta()) {
                itemsToString += itemStack.getItemMeta().getDisplayName() + " x" + items.get(itemStack);
            } else {
                itemsToString += itemStack.getType().toString() + " x" + items.get(itemStack);
            }
        }

        return "Kit " + this.name + " contains : " + itemsToString;
    }
}
