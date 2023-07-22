package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;

public class ListKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (HungerGameStateManager.currentState == HungerGameStateManager.State.NOT_STARTED) {
            sender.sendMessage(ChatColor.GOLD + "Here are the available kits");
            for (Kit kit : KitManager.getKits()) {
                sender.sendMessage(kit.toString());
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You can't change kit while the game is running");
        }



        return true;
    }
}
