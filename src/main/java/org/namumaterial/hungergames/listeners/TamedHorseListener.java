package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class TamedHorseListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player tamedHorseUser = event.getPlayer();
        ItemStack item = tamedHorseUser.getInventory().getItemInMainHand();

        if (hasSaddleInHand(item) && isRightClickOnBlockAction(event)) {
            defineRightClickAction(event, tamedHorseUser);
            removeTamedHorseFromInventory(tamedHorseUser, item);
        }
    }

    private static boolean hasSaddleInHand(ItemStack item) {
        return item.getType() == Material.SADDLE;
    }

    private static boolean isRightClickOnBlockAction(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_BLOCK;
    }

    private void defineRightClickAction(PlayerInteractEvent event, Player tamedHorseUser) {
        Block clickedBlock = event.getClickedBlock();
        Location clickedBlockLocation = clickedBlock.getLocation();

        final double xAndZReadjustment = 0.5;
        final double yReadjustment = 1.0;

        clickedBlockLocation.add(xAndZReadjustment, yReadjustment, xAndZReadjustment);

        spawnTamedHorse(tamedHorseUser, clickedBlockLocation);
    }

    private static void spawnTamedHorse(Player tamedHorseUser, Location clickedBlockLocation) {
        final String horseName = tamedHorseUser.getName() + "'s horse";

        Horse horse = (Horse) tamedHorseUser.getWorld().spawnEntity(clickedBlockLocation, EntityType.HORSE);
        horse.setTamed(true);
        horse.getInventory().addItem(new ItemStack(Material.SADDLE, 1));
        horse.setCustomName(horseName);
        horse.setAdult();
        horse.setColor(Horse.Color.CREAMY);
        horse.setStyle(Horse.Style.BLACK_DOTS);
        horse.setMaxHealth(40.0);
        horse.setHealth(40.0);
        horse.setJumpStrength(1.0);
    }

    private void removeTamedHorseFromInventory(Player tamedHorseUser, ItemStack tamedHorse) {
        tamedHorse.setAmount(tamedHorse.getAmount() - 1);
        tamedHorseUser.updateInventory();
    }

    @EventHandler
    public void onPlayerOpenTamedHorseInventory(InventoryOpenEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();

        if (inventoryHolder instanceof Horse) {
            event.setCancelled(true);
        }
    }
}
