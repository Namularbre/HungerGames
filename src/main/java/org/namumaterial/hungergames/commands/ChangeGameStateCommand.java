package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class ChangeGameStateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            PlayerRawMessageSender.sendErrorMessage("This command need one argument. It is used like this : /change_state [state]", player);
            return true;
        }

        if (!player.isOp()) {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
            return true;
        }

        final String HG_STATE = args[0];

        HungerGameStateManager.State state = HungerGameStateManager.stateFromString(HG_STATE);

        if (state != null) {
            HungerGameStateManager.setCurrentStep(state);
            PlayerRawMessageSender.sendValidationMessage("Done", player);

            if (state == HungerGameStateManager.State.STARTING) {
                KitManager.giveKitToPlayers();
            }

        } else {
            PlayerRawMessageSender.sendErrorMessage("Unknown state", player);
        }

        return true;
    }
}
