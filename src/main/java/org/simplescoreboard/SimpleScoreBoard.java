package org.simplescoreboard;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.simplescoreboard.commands.SBCommand;
import org.simplescoreboard.listeners.PlayerJoinListener;
import org.simplescoreboard.scoreboard.ScoreboardBuilder;
import org.simplescoreboard.scoreboard.ScoreboardConfig;
import org.simplescoreboard.scoreboard.ScoreboardManager;

public final class SimpleScoreBoard extends JavaPlugin {

    private ScoreboardConfig sbConfig;
    private ScoreboardBuilder sbBuilder;
    private ScoreboardManager sbManager;

    @Override
    public void onEnable() {
        getLogger().info("SimpleScoreboard initialized!");

        // Initialisation des outils
        sbConfig = new ScoreboardConfig(this);
        sbBuilder = new ScoreboardBuilder(sbConfig);
        sbManager = new ScoreboardManager(sbBuilder);

        // Tâche de mise à jour régulière
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Player player : getServer().getOnlinePlayers()) {
                sbManager.updateScoreboard(player);
            }
        }, 20L * sbConfig.getUpdateInterval(), 20L * sbConfig.getUpdateInterval());

        // Commande /sb reload
        SBCommand sbCommand = new SBCommand(this);
        getCommand("sb").setExecutor(sbCommand);
        getCommand("sb").setTabCompleter(sbCommand);


        // Événement de connexion
        getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(this, sbManager), this
        );
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            sbManager.removeScoreboard(player);
        }
    }

    public ScoreboardConfig getSbConfig() {
        return sbConfig;
    }

    public ScoreboardBuilder getSbBuilder() {
        return sbBuilder;
    }

    public ScoreboardManager getSbManager() {
        return sbManager;
    }

    public void setSbBuilder(ScoreboardBuilder builder) {
        this.sbBuilder = builder;
    }

    public void setSbManager(ScoreboardManager manager) {
        this.sbManager = manager;
    }
}
