package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.managers.TributeManager;

public class SelectKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (HungerGameStateManager.currentState == HungerGameStateManager.State.NOT_STARTED) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "This command require the kit name : /kit [name]");
                return true;
            }
            final String KIT_NAME = args[0];

            Kit selectedKit = KitManager.getKitByName(KIT_NAME);

            if (selectedKit != null) {
                TributeManager.getTribute(player).setKit(selectedKit);

                player.sendMessage("Kit " + KIT_NAME + " selected");
            } else {
                TributeManager.getTribute(player).setKit(KitManager.getRandomKit());

                player.sendRawMessage(ChatColor.RED + "The kit doesn't exists. You have now a random one");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can't change kit while the game is started");
        }

        return true;
    }
}
