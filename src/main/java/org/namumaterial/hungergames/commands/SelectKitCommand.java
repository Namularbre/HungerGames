package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.kits.Kit;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class SelectKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (HungerGameStateManager.gameIsNotStarted()) {
            if (args.length != 1) {
                PlayerRawMessageSender.sendErrorMessage("This command require the kit name : /kit [name]", player);
                return true;
            }
            final String KIT_NAME = args[0];

            Kit selectedKit = KitManager.getKitByName(KIT_NAME);

            if (selectedKit != null) {
                TributeManager.getTribute(player).setKit(selectedKit);

                PlayerRawMessageSender.sendInformationMessage("Kit " + KIT_NAME + " selected", player);
            } else {
                TributeManager.getTribute(player).setKit(KitManager.getRandomKit());

                PlayerRawMessageSender.sendErrorMessage("The kit doesn't exists. You have now a random one", player);
            }
        } else {
            PlayerRawMessageSender.sendErrorMessage("You can't change kit while the game is started", player);
        }

        return true;
    }
}
