package org.namumaterial.hungergames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.File;

public class ServerStopListener implements Listener {

    @EventHandler
    public void onServerStop(ServerCommandEvent event) {
        if (event.getCommand().equalsIgnoreCase("stop")) {
            File worldFolder = new File(Bukkit.getServer().getWorldContainer(), "world");
            File netherFolder = new File(Bukkit.getServer().getWorldContainer(), "world_nether");
            File endFolder = new File(Bukkit.getServer().getWorldContainer(), "world_end");

            removeFiles(worldFolder);
            removeFiles(netherFolder);
            removeFiles(endFolder);

            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Worlds folder are removed !");
        }
    }

    private void removeFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    removeFiles(file);
                } else {
                    file.delete();
                }
            }
        }

        folder.delete();
    }
}
