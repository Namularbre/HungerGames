package org.namumaterial.hungergames.tasks;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class DisplayHelpTask extends BukkitRunnable {
    private final static List<String> help = new ArrayList<>(Arrays.asList(new String[]{
        "You can spawn a tamed horse with a saddle by right clicking on a block.",
        "When placed, TNT will immediately be ignited and will explode in 1.5 seconds",
        "You can launch a fireball when right clicking with a fire charge. But don't spam them, or they will explode you instead !",
        "The compass will point at nearest player. If you right click, it will give the distance between you and that player.",
        "This plugin doesn't allow the crafting of shield. You're a grown man, you can survive some injuries !",
        "When you do some actions, you will start to become popular. If you are famous enough, you can have a gift from sponsors.",
        "Feast spawn randomly near the spawn point. They are full of useful stuff, like diamond tools/armor, saddle, tnt or fire charge"
    }));

    @Override
    public void run() {
        Random random = new Random();
        final int NEXT_HELP_INDEX = random.nextInt(help.size());

        Bukkit.getServer().broadcastMessage(help.get(NEXT_HELP_INDEX));
    }
}
