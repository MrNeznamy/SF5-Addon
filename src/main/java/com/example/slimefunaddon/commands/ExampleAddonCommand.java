package com.example.slimefunaddon.commands;

import com.example.slimefunaddon.ExampleAddon;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command handler for the Example Addon
 */
public class ExampleAddonCommand implements CommandExecutor, TabCompleter {

    private final ExampleAddon plugin;

    public ExampleAddonCommand(ExampleAddon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "help":
                sendHelp(sender);
                break;
            case "info":
                sendInfo(sender);
                break;
            case "reload":
                if (sender.hasPermission("exampleaddon.admin")) {
                    sender.sendMessage(ChatColor.GREEN + "Example Addon reloaded successfully!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to reload the addon!");
                }
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Unknown subcommand. Use /exampleaddon help for help.");
                break;
        }

        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== Example Slimefun Addon Help ===");
        sender.sendMessage(ChatColor.YELLOW + "/exampleaddon help" + ChatColor.WHITE + " - Show this help message");
        sender.sendMessage(ChatColor.YELLOW + "/exampleaddon info" + ChatColor.WHITE + " - Show addon information");
        if (sender.hasPermission("exampleaddon.admin")) {
            sender.sendMessage(ChatColor.YELLOW + "/exampleaddon reload" + ChatColor.WHITE + " - Reload the addon");
        }
    }

    private void sendInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== Example Slimefun Addon Info ===");
        sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.WHITE + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.AQUA + "Author: " + ChatColor.WHITE + plugin.getDescription().getAuthors());
        sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + plugin.getDescription().getDescription());
        sender.sendMessage(ChatColor.GREEN + "This addon adds custom machines, generators, and items to Slimefun!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>(Arrays.asList("help", "info"));
            if (sender.hasPermission("exampleaddon.admin")) {
                completions.add("reload");
            }
            return completions;
        }
        return new ArrayList<>();
    }
}