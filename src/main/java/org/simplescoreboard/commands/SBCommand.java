package org.simplescoreboard.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.simplescoreboard.SimpleScoreBoard;
import org.simplescoreboard.scoreboard.ScoreboardBuilder;
import org.simplescoreboard.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SBCommand implements CommandExecutor, TabCompleter {

    private final SimpleScoreBoard plugin;

    public SBCommand(SimpleScoreBoard plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§eUsage: /sb <reload|toggle>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("simplescoreboard.admin")) {
                sender.sendMessage("§cYou don't have permission to do this.");
                return true;
            }

            plugin.getSbConfig().reload();
            ScoreboardBuilder newBuilder = new ScoreboardBuilder(plugin.getSbConfig());
            plugin.setSbBuilder(newBuilder);

            ScoreboardManager newManager = new ScoreboardManager(newBuilder);
            plugin.setSbManager(newManager);

            for (Player player : plugin.getServer().getOnlinePlayers()) {
                newManager.createScoreboard(player);
            }

            sender.sendMessage("§a[SimpleScoreBoard] Configuration reloaded and scoreboards updated.");
            return true;
        }

        // Future toggle handling can go here

        sender.sendMessage("§eUnknown argument. Use /sb <reload>");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if (sender.hasPermission("simplescoreboard.admin")) {
                completions.addAll(Arrays.asList("reload", "toggle"));
            }
            return completions;
        }
        return Collections.emptyList();
    }
}
