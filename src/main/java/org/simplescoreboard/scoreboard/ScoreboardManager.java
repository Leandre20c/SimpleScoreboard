
package org.simplescoreboard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {

    private final ScoreboardBuilder sbBuilder;
    private final Map<Player, Scoreboard> activeBoards = new HashMap<>();

    public ScoreboardManager(ScoreboardBuilder sbBuilder) {
        this.sbBuilder = sbBuilder;
    }

    public void createScoreboard(Player player){
        Scoreboard sb = sbBuilder.buildScoreboard(player);
        player.setScoreboard(sb);
        activeBoards.put(player, sb);
    }

    public void updateScoreboard(Player player){
        if (!activeBoards.containsKey(player)) return;
        Scoreboard updated = sbBuilder.buildScoreboard(player);
        player.setScoreboard(updated);
        activeBoards.put(player, updated);
    }

    public void removeScoreboard(Player player){
        if (activeBoards.containsKey(player)) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            activeBoards.remove(player);
        }
    }

    public boolean hasScoreboard(Player player){
        return activeBoards.containsKey(player);
    }

    public void getOrCreate(Player player){
        if (!hasScoreboard(player)) {
            createScoreboard(player);
        }
    }

    public void updateAll() {
        for (Player player : activeBoards.keySet()) {
            updateScoreboard(player);
        }
    }
}
