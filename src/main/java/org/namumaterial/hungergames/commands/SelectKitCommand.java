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

        if (!TributeManager.isTribute(player)) {
            PlayerRawMessageSender.sendErrorMessage("You are not a tribute", player);
            return true;
        }

        if (args.length == 1 && HungerGameStateManager.gameIsNotStarted()) {
            final String KIT_NAME = args[0];

            Kit selectedKit = KitManager.getKitByName(KIT_NAME);

            if (selectedKit != null) {
                TributeManager.getTribute(player).setKit(selectedKit);

                PlayerRawMessageSender.sendInformationMessage("Kit " + KIT_NAME + " selected", player);
            } else {
                PlayerRawMessageSender.sendErrorMessage("The kit doesn't exist. Do /kits to see available kits", player);
            }
        } else {
            final Tribute TRIBUTE = TributeManager.getTribute(player);
            final String KIT_NAME = TRIBUTE.getKit().getName();
            PlayerRawMessageSender.sendInformationMessage("Current kit: " + KIT_NAME, player);
        }

        return true;
    }
}
