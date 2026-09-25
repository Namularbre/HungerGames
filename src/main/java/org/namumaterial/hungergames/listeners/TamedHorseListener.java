package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.namumaterial.hungergames.utils.HungerGamesConfiguration;

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
        horse.setOwner(tamedHorseUser);
        horse.getInventory().addItem(new ItemStack(Material.SADDLE, 1));
        horse.setCustomName(horseName);
        horse.setAdult();
        horse.setColor(Horse.Color.CREAMY);
        horse.setStyle(Horse.Style.BLACK_DOTS);
        // The max health must be set first : a vanilla horse has a random max health (15 to 30),
        // and setHealth() throws an exception above it
        horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(HungerGamesConfiguration.HORSE_HEALTH);
        horse.setHealth(HungerGamesConfiguration.HORSE_HEALTH);
        horse.setJumpStrength(HungerGamesConfiguration.HORSE_JUMP_STRENGTH);
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
