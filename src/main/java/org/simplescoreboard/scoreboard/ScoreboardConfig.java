package org.simplescoreboard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class ScoreboardConfig {

    private final JavaPlugin plugin;
    private FileConfiguration config;

    public ScoreboardConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.reload();
    }

    public void reload() {
        plugin.saveDefaultConfig(); // Crée config.yml s’il n’existe pas
        this.config = plugin.getConfig();
    }

    public int getUpdateInterval() {
        return config.getInt("update-interval", 5);
    }

    public String getTitleForWorld(String worldName) {
        if (!config.contains("scoreboards." + worldName)) {
            Bukkit.getLogger().warning("[SimpleScoreBoard] No config found for world '" + worldName + "', using default.");
        }
        String path = "scoreboards." + worldName + ".title";
        if (config.contains(path)) {
            return config.getString(path);
        }
        return config.getString("scoreboards.default.title", "§aSimpleBoard");
    }

    public List<String> getLinesForWorld(String worldName) {
        if (!config.contains("scoreboards." + worldName)) {
            Bukkit.getLogger().warning("[SimpleScoreBoard] No lines found for world '" + worldName + "', using default.");
        }
        String path = "scoreboards." + worldName + ".lines";
        if (config.contains(path)) {
            return config.getStringList(path);
        }
        return config.getStringList("scoreboards.default.lines");
    }

    public Map<String, Object> getAllScoreboardsRaw() {
        return config.getConfigurationSection("scoreboards").getValues(false);
    }
}
