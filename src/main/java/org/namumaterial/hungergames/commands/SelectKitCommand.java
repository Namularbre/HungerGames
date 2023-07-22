package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

public class SelectKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (HungerGameStateManager.currentState == HungerGameStateManager.State.NOT_STARTED) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "This command require de kit name : /kit [name]");
                return true;
            }
            final String KIT_NAME = args[0];

            sender.sendMessage("Kit " + KIT_NAME + " selected");
        } else {
            sender.sendMessage(ChatColor.RED + "You can't change kit while the game is started");
        }

        return true;
    }
}
