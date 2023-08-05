package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Fireball;

public class FireballListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (playerHasFireBallInHand(event, player)) {
            event.setCancelled(true);

            ItemStack fireBallItem = player.getInventory().getItemInMainHand();

            final int EXPLOSION_YIELD = 3;

            Fireball fireball = spawnGhastFireBall(player);
            fireball.setShooter(player);
            fireball.setYield(EXPLOSION_YIELD); //Set explosion efficiency (range/damage)

            // Defines the direction and speed of the fireball depending on the direction the player is looking in
            fireball.setDirection(player.getEyeLocation().getDirection().multiply(2));
            fireBallItem.setAmount(fireBallItem.getAmount() - 1);

            player.getInventory().setItemInMainHand(fireBallItem);
            player.updateInventory();
        }
    }

    private static Fireball spawnGhastFireBall(Player player) {
        return (Fireball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.FIREBALL);
    }

    private static boolean playerHasFireBallInHand(PlayerInteractEvent event, Player player) {
        return isRightClickEvent(event) && player.getInventory().getItemInMainHand().getType() == Material.FIRE_CHARGE;
    }

    private static boolean isRightClickEvent(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
    }

}
