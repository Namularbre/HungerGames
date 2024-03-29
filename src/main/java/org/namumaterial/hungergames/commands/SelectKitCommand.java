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
import org.namumaterial.hungergames.utils.Tribute;

public class SelectKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && HungerGameStateManager.gameIsNotStarted()) {
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
            final Tribute TRIBUTE = TributeManager.getTribute(player);
            final String KIT_NAME = TRIBUTE.getKit().getName();
            PlayerRawMessageSender.sendInformationMessage("Your kit is " + KIT_NAME, player);
        }

        return true;
    }
}
