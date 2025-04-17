package org.simplescoreboard.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerJoinListener implements Listener {

    private final org.simplescoreboard.scoreboard.ScoreboardManager sbManager;

    public PlayerJoinListener(JavaPlugin plugin, org.simplescoreboard.scoreboard.ScoreboardManager sbManager) {
        this.sbManager = sbManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        sbManager.createScoreboard(player);
    }
}
