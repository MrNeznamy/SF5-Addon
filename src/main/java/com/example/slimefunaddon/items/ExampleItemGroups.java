package com.example.slimefunaddon.items;

import com.example.slimefunaddon.ExampleAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import eu.mrneznamy.slimefun5.items.CustomItemStack;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

/**
 * Contains all ItemGroups for the Example Addon
 * 
 * This class demonstrates how to create and customize ItemGroups in your addon.
 * 
 * CUSTOM DESCRIPTIONS:
 * You can now set custom descriptions for your categories using the setDescription() method.
 * This allows you to:
 * - Override default localization with your own text
 * - Provide detailed explanations for your categories
 * - Customize the user experience without modifying language files
 * 
 * Example usage:
 * ItemGroup myCategory = new ItemGroup(key, item);
 * myCategory.setDescription("&7Your custom description here!");
 * 
 * If no custom description is set, the system will fall back to localization files.
 * 
 * RAW MODE CATEGORIES:
 * You can now create "raw" categories without automatic formatting using the new constructor:
 * ItemGroup rawCategory = new ItemGroup(key, customItemStack, tier, true);
 * 
 * Raw mode features:
 * - No automatic color schemes applied
 * - No automatic lore formatting
 * - No automatic localization
 * - Developer has full control over ItemStack appearance
 * - Perfect for custom designs and exact formatting control
 * 
 * When to use RAW MODE:
 * - When you want exact control over colors and formatting
 * - When you have a specific design that shouldn't be modified
 * - When you want to bypass the automatic Slimefun styling
 * - When creating themed categories with custom appearance
 * 
 * When to use MANAGED MODE (default):
 * - When you want consistent Slimefun styling
 * - When you want automatic localization support
 * - When you want the system to handle color schemes
 * - For most standard addon categories
 */
public class ExampleItemGroups {

    // Main category - initialized in setup method
    public static NestedItemGroup EXAMPLE_ADDON;

    // Sub-categories - initialized in setup method
    public static SubItemGroup RESOURCES;
    public static SubItemGroup MACHINES;
    public static SubItemGroup ENERGY;
    public static SubItemGroup TOOLS;
    
    // Raw category - demonstrates raw mode without automatic formatting
    public static ItemGroup RAW_CATEGORY;

    /**
     * Setup and register all item groups
     * @param plugin The plugin instance
     */
    public static void setup(ExampleAddon plugin) {
        // Initialize the main category
        EXAMPLE_ADDON = new NestedItemGroup(
            new NamespacedKey(plugin, "example_addon"),
            new CustomItemStack(Material.DIAMOND_PICKAXE, ColorSystem.colorize("&bExample Addon"), 
                "", ColorSystem.colorize("&7A collection of example items"), ColorSystem.colorize("&7for Slimefun 5 addon development"))
        );
        
        // Example: Set custom description for the main category
        // This will override any localization and show this custom text
        EXAMPLE_ADDON.setDescription(ColorSystem.colorize("&7Welcome to the Example Addon! This category contains " +
            "&7various example items to demonstrate Slimefun 5 addon development. " +
            "&7Feel free to explore and learn from these examples!"));

        // Initialize sub-categories
        RESOURCES = new SubItemGroup(
            new NamespacedKey(plugin, "resources"),
            EXAMPLE_ADDON,
            new CustomItemStack(Material.IRON_INGOT, ColorSystem.colorize("&bResources"), 
                "", ColorSystem.colorize("&7Custom ores, ingots and materials"))
        );
        
        // Example: Set custom description for resources category
        RESOURCES.setDescription(ColorSystem.colorize("&7This category contains custom ores, ingots, and materials. " +
            "&7These resources can be used in various recipes and machines throughout the addon."));

        MACHINES = new SubItemGroup(
            new NamespacedKey(plugin, "machines"),
            EXAMPLE_ADDON,
            new CustomItemStack(Material.FURNACE, ColorSystem.colorize("&bMachines"), 
                "", ColorSystem.colorize("&7Custom machines and processors"))
        );
        
        // Example: Set custom description for machines category
        MACHINES.setDescription(ColorSystem.colorize("&7Here you'll find custom machines and processors. " +
            "&7These machines can automate various tasks and create new items!"));

        ENERGY = new SubItemGroup(
            new NamespacedKey(plugin, "energy"),
            EXAMPLE_ADDON,
            new CustomItemStack(Material.REDSTONE_BLOCK, ColorSystem.colorize("&bEnergy"), 
                "", ColorSystem.colorize("&7Generators and energy-related items"))
        );
        
        // Example: Set custom description for energy category
        ENERGY.setDescription(ColorSystem.colorize("&7This category focuses on energy generation and management. " +
            "&7Use these generators to power your machines and create sustainable energy systems!"));

        TOOLS = new SubItemGroup(
            new NamespacedKey(plugin, "tools"),
            EXAMPLE_ADDON,
            new CustomItemStack(Material.DIAMOND_SWORD, ColorSystem.colorize("&bTools &amp; Weapons"), 
                "", ColorSystem.colorize("&7Custom tools and weapons"))
        );
        
        // Example: Set custom description for tools category
        TOOLS.setDescription(ColorSystem.colorize("&7Discover powerful custom tools and weapons here. " +
            "&7These items will help you in your adventures and make your gameplay more exciting!"));

        // ========================================
        // RAW CATEGORY EXAMPLE
        // ========================================
        // This demonstrates how to create a "raw" category without automatic formatting.
        // In raw mode, you have full control over the ItemStack appearance - no automatic
        // color schemes, lore formatting, or localization is applied.
        
        // Create a custom ItemStack with your own colors and lore
        ItemStack rawCategoryItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta rawMeta = rawCategoryItem.getItemMeta();
        if (rawMeta != null) {
            // Set your own custom name with exact colors you want
            rawMeta.setDisplayName("§6§l✦ §e§lRAW CATEGORY §6§l✦");
            
            // Set your own custom lore with exact formatting
            rawMeta.setLore(Arrays.asList(
                "",
                "§7This is a §c§lRAW CATEGORY §7example!",
                "§7No automatic formatting is applied here.",
                "",
                "§a§l✓ §aFull control over colors",
                "§a§l✓ §aCustom lore formatting", 
                "§a§l✓ §aNo automatic localization",
                "§a§l✓ §aExact appearance as designed",
                "",
                "§e§lDeveloper has complete control!",
                "§8§o(This demonstrates raw mode usage)"
            ));
            
            rawCategoryItem.setItemMeta(rawMeta);
        }
        
        // Create the raw category using the new constructor with rawMode = true
        RAW_CATEGORY = new ItemGroup(
            new NamespacedKey(plugin, "raw_category"),
            rawCategoryItem,
            2, // tier
            null, // customDescription (not needed for this example)
            true // rawMode = true (no automatic formatting)
        );

        // Register the main category first
        EXAMPLE_ADDON.register(plugin);
        
        // Then register all sub-categories
        RESOURCES.register(plugin);
        MACHINES.register(plugin);
        ENERGY.register(plugin);
        TOOLS.register(plugin);
        
        // Register the raw category
        RAW_CATEGORY.register(plugin);
        
        plugin.logInfo("Item groups registered successfully!");
    }
}