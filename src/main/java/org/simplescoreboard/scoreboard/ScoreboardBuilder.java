
package org.simplescoreboard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Collections;
import java.util.List;

import me.clip.placeholderapi.PlaceholderAPI;


public class ScoreboardBuilder {

    private final ScoreboardConfig sbConfig;

    public ScoreboardBuilder(ScoreboardConfig sbConfig) {
        this.sbConfig = sbConfig;
    }

    public Scoreboard buildScoreboard(Player player){
        final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sb", "dummy", sbConfig.getTitleForWorld(player.getWorld().getName()));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> lines = sbConfig.getLinesForWorld(player.getWorld().getName());

        int score = 15;
        int emptyCount = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                line = " ".repeat(++emptyCount);
            }
            
            // Intégration PlaceholderAPI
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                line = PlaceholderAPI.setPlaceholders(player, line);
            }
            objective.getScore(colorize(line)).setScore(score--);

        }

        return scoreboard;
    }

    private String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
