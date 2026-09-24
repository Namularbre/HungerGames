package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Cake;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.utils.SecondToTicksConverter;

import java.util.Random;

public class PlayerEatCakeListener implements Listener {
    private final PotionEffect[] potionEffects;

    public PlayerEatCakeListener() {
        potionEffects = new PotionEffect[]{
                new PotionEffect(PotionEffectType.BLINDNESS, SecondToTicksConverter.convert(20), 1),
                new PotionEffect(PotionEffectType.ABSORPTION, SecondToTicksConverter.convert(60), 4),
                new PotionEffect(PotionEffectType.SPEED, SecondToTicksConverter.convert(120), 3),
                new PotionEffect(PotionEffectType.REGENERATION, SecondToTicksConverter.convert(120), 12),
                new PotionEffect(PotionEffectType.GLOWING, SecondToTicksConverter.convert(120), 1),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, SecondToTicksConverter.convert(60), 3),
                new PotionEffect(PotionEffectType.INVISIBILITY, SecondToTicksConverter.convert(30), 1)
        };
    }

    @EventHandler
    public void onPlayerEatCake(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && HungerGameStateManager.gameIsLaunched()) {
            if (block.getType() == Material.CAKE) {
                // Vanilla eating is replaced, because it doesn't let the player eat when their food bar is full
                event.setCancelled(true);

                // The event is fired once per hand : only eat once
                if (event.getHand() != EquipmentSlot.HAND) {
                    return;
                }

                eatCakeSlice(player, block);
                player.addPotionEffect(getRandomPotionEffect());
            }
        }
    }

    private static void eatCakeSlice(Player player, Block cakeBlock) {
        // Same food and saturation as a vanilla cake slice
        final int FOOD_PER_SLICE = 2;
        final float SATURATION_PER_SLICE = 0.4F;
        final int MAX_FOOD_LEVEL = 20;

        Cake cake = (Cake) cakeBlock.getBlockData();

        if (cake.getBites() >= cake.getMaximumBites()) {
            cakeBlock.setType(Material.AIR);
        } else {
            cake.setBites(cake.getBites() + 1);
            cakeBlock.setBlockData(cake);
        }

        final int NEW_FOOD_LEVEL = Math.min(player.getFoodLevel() + FOOD_PER_SLICE, MAX_FOOD_LEVEL);
        player.setFoodLevel(NEW_FOOD_LEVEL);
        // Saturation can't be higher than the food level
        player.setSaturation(Math.min(player.getSaturation() + SATURATION_PER_SLICE, NEW_FOOD_LEVEL));

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
    }

    private PotionEffect getRandomPotionEffect() {
        Random random = new Random();
        final int RANDOM_POTION_EFFECT_INDEX = random.nextInt(potionEffects.length);

        return potionEffects[RANDOM_POTION_EFFECT_INDEX];
    }


}
