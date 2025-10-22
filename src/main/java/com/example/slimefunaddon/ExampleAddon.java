package com.example.slimefunaddon;

import com.example.slimefunaddon.items.ExampleItems;
import com.example.slimefunaddon.commands.ExampleAddonCommand;
import com.example.slimefunaddon.items.ExampleItemGroups;
import com.example.slimefunaddon.items.ExampleItems;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Main class for the Example Slimefun 5 Addon
 * 
 * This addon demonstrates:
 * - Custom resources and materials
 * - Custom machines (multiblock, electric consumer, generator)
 * - Custom solar panels
 * - Custom tools and weapons
 * - Proper item registration and recipes
 */
public class ExampleAddon extends JavaPlugin implements SlimefunAddon {

    private static ExampleAddon instance;

    @Override
    public void onEnable() {
        instance = this;
        logInfo("Example Addon is starting up...");
        
        // Setup item groups
        ExampleItemGroups.setup(this);
        
        // Register items
        ExampleItems.setup(this);
        
        logInfo("Example Addon has been enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Example Slimefun Addon has been disabled!");
        instance = null;
    }

    /**
     * Get the plugin instance
     * @return The plugin instance
     */
    public static ExampleAddon getInstance() {
        return instance;
    }

    /**
     * Get the Slimefun addon instance
     * @return This addon instance
     */
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    /**
     * Get the bug tracker URL
     * @return Bug tracker URL
     */
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/example/slimefun-example-addon/issues";
    }

    /**
     * Create a NamespacedKey for this addon
     * @param key The key string
     * @return A NamespacedKey for this addon
     */
    public NamespacedKey getKey(String key) {
        return new NamespacedKey(this, key);
    }

    /**
     * Log a message with the specified level
     * @param level The log level
     * @param message The message to log
     */
    public void log(Level level, String message) {
        getLogger().log(level, message);
    }

    /**
     * Log an info message
     * @param message The message to log
     */
    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    /**
     * Log a warning message
     * @param message The message to log
     */
    public void logWarning(String message) {
        log(Level.WARNING, message);
    }

    /**
     * Log a severe message
     * @param message The message to log
     */
    public void logSevere(String message) {
        log(Level.SEVERE, message);
    }
}