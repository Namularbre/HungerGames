package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.HungerGameStateManager;

public class ChangeGameStateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendRawMessage(ChatColor.RED + "This command need one argument. It is used like this : /change_state [state]");
        }

        if (!player.isOp()) {
            player.sendRawMessage(ChatColor.RED + "You don't have the permission to use this command");
            return true;
        }

        final String HG_STATE = args[0];

        HungerGameStateManager.State state = HungerGameStateManager.stateFromString(HG_STATE);

        if (state != null) {
            HungerGameStateManager.setCurrentStep(state);
            player.sendRawMessage(ChatColor.GREEN + "worked"); //TODO: remove this
        } else {
            player.sendRawMessage(ChatColor.RED + "Unknown state");
        }

        return true;
    }
}
