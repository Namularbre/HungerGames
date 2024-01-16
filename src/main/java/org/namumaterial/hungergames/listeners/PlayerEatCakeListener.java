package org.namumaterial.hungergames.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
                player.addPotionEffect(getRandomPotionEffect());
            }
        }
    }

    private PotionEffect getRandomPotionEffect() {
        Random random = new Random();
        final int RANDOM_POTION_EFFECT_INDEX = random.nextInt(potionEffects.length);

        return potionEffects[RANDOM_POTION_EFFECT_INDEX];
    }


}
