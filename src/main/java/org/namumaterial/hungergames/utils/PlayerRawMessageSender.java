package org.namumaterial.hungergames.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerRawMessageSender {
    public static void sendInformationMessage(String messageContent, Player player) {
        player.sendRawMessage(messageContent);
    }

    public static void sendValidationMessage(String messageContent, Player player) {
        player.sendRawMessage(ChatColor.GREEN + messageContent);
    }

    public static void sendErrorMessage(String messageContent, Player player) {
        player.sendRawMessage(ChatColor.RED + messageContent);
    }

    public static void sendNoCommandPermissionMessage(Player player) {
        sendErrorMessage("You don't have the permission to use this command", player);
    }
}
