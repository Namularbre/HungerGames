package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class ListKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (HungerGameStateManager.gameIsNotStarted()) {
            StringBuilder message = new StringBuilder("Here are the available kits");

            for (Kit kit : KitManager.getKits()) {
                message.append(kit.toString()).append("\n");
            }

            PlayerRawMessageSender.sendInformationMessage(message.toString(), player);
        } else {
            PlayerRawMessageSender.sendErrorMessage("You can't change kit while the game is running", player);
        }

        return true;
    }
}
