package com.example.slimefunaddon.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Example item that demonstrates the use of custom inventory system.
 * This item opens a custom GUI when right-clicked, showing various interactive elements.
 * 
 * This serves as a practical example for addon developers on how to integrate
 * with the Slimefun inventory system.
 */
public class ExampleInventoryItem extends SlimefunItem {

    /**
     * Constructor for the ExampleInventoryItem
     * 
     * @param itemGroup The item group this item belongs to
     * @param item The SlimefunItemStack representing this item
     * @param recipeType The recipe type for crafting this item
     * @param recipe The recipe array for crafting this item
     */
    public ExampleInventoryItem(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, 
                               @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        // Add the ItemUseHandler for right-click functionality
        addItemHandler(getItemUseHandler());
    }

    /**
     * Creates and returns the ItemUseHandler for this item.
     * This handler is triggered when a player right-clicks with the item.
     * 
     * @return ItemUseHandler that opens the custom inventory
     */
    @Nonnull
    private ItemUseHandler getItemUseHandler() {
        return e -> {
            // Cancel the event to prevent default behavior
            e.cancel();
            
            Player player = e.getPlayer();
            
            // Play a sound to give feedback to the player
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f);
            
            // Open the custom inventory
            openCustomInventory(player);
        };
    }

    /**
     * Opens a custom inventory GUI for the player using the custom inventory system.
     * This demonstrates various features like buttons, information displays, and interactive elements.
     * 
     * @param player The player to open the inventory for
     */
    private void openCustomInventory(@Nonnull Player player) {
        // Create a new ChestMenu with 27 slots (3 rows)
        ChestMenu menu = new ChestMenu(ColorSystem.colorize("&bExample Inventory Tool"));
        
        // Fill the background with glass panes for a clean look
        fillBackground(menu);
        
        // Add information display
        addInformationDisplay(menu);
        
        // Add interactive buttons
        addInteractiveButtons(menu, player);
        
        // Add navigation and utility buttons
        addNavigationButtons(menu, player);
        
        // Open the menu for the player
        menu.open(player);
    }

    /**
     * Fills the background of the menu with glass panes for a clean appearance.
     * 
     * @param menu The ChestMenu to fill
     */
    private void fillBackground(@Nonnull ChestMenu menu) {
        // Use ChestMenuUtils background item for consistency
        ItemStack background = ChestMenuUtils.getBackground();
        
        // Fill all slots with background initially
        for (int i = 0; i < 27; i++) {
            menu.addItem(i, background, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    /**
     * Adds information display items to the menu.
     * These items show information about the tool and its capabilities.
     * 
     * @param menu The ChestMenu to add items to
     */
    private void addInformationDisplay(@Nonnull ChestMenu menu) {
        // Title item
        ItemStack titleItem = new ItemStack(Material.NAME_TAG);
        ItemMeta titleMeta = titleItem.getItemMeta();
        if (titleMeta != null) {
            titleMeta.setDisplayName(ColorSystem.colorize("&bExample Inventory Tool"));
            titleMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7This is an example of how to use menu"),
                ColorSystem.colorize("&7in your Slimefun addons."),
                "",
                ColorSystem.colorize("&aTry clicking the buttons below!")
            ));
            titleItem.setItemMeta(titleMeta);
        }
        menu.addItem(4, titleItem, ChestMenuUtils.getEmptyClickHandler());
        
        // Status item
        ItemStack statusItem = new ItemStack(Material.EMERALD);
        ItemMeta statusMeta = statusItem.getItemMeta();
        if (statusMeta != null) {
            statusMeta.setDisplayName(ColorSystem.colorize("&aStatus: Active"));
            statusMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7This tool is currently active"),
                ColorSystem.colorize("&7and ready to demonstrate"),
                ColorSystem.colorize("&7inventory functionality.")
            ));
            statusItem.setItemMeta(statusMeta);
        }
        menu.addItem(13, statusItem, ChestMenuUtils.getEmptyClickHandler());
    }

    /**
     * Adds interactive buttons that perform actions when clicked.
     * 
     * @param menu The ChestMenu to add buttons to
     * @param player The player who will interact with the buttons
     */
    private void addInteractiveButtons(@Nonnull ChestMenu menu, @Nonnull Player player) {
        // Action Button 1: Give player an item
        ItemStack giveItemButton = new ItemStack(Material.DIAMOND);
        ItemMeta giveMeta = giveItemButton.getItemMeta();
        if (giveMeta != null) {
            giveMeta.setDisplayName(ColorSystem.colorize("&bGet Diamond"));
            giveMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7Click to receive a diamond!"),
                ColorSystem.colorize("&7This demonstrates item giving"),
                ColorSystem.colorize("&7functionality in custom menus."),
                "",
                ColorSystem.colorize("&aClick to activate")
            ));
            giveItemButton.setItemMeta(giveMeta);
        }
        menu.addItem(10, giveItemButton, (p, slot, item, action) -> {
            p.getInventory().addItem(new ItemStack(Material.DIAMOND));
            p.sendMessage(ColorSystem.colorize("&aYou received a diamond!"));
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            return false; // Don't close the menu
        });
        
        // Action Button 2: Heal player
        ItemStack healButton = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta healMeta = healButton.getItemMeta();
        if (healMeta != null) {
            healMeta.setDisplayName(ColorSystem.colorize("&bHeal Player"));
            healMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7Click to restore your health!"),
                ColorSystem.colorize("&7This demonstrates player"),
                ColorSystem.colorize("&7manipulation in menus."),
                "",
                ColorSystem.colorize("&aClick to activate")
            ));
            healButton.setItemMeta(healMeta);
        }
        menu.addItem(12, healButton, (p, slot, item, action) -> {
            p.setHealth(p.getMaxHealth());
            p.sendMessage(ColorSystem.colorize("&aYou have been healed!"));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            return false; // Don't close the menu
        });
        
        // Action Button 3: Show player info
        ItemStack infoButton = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = infoButton.getItemMeta();
        if (infoMeta != null) {
            infoMeta.setDisplayName(ColorSystem.colorize("&bPlayer Info"));
            infoMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7Click to see your information!"),
                ColorSystem.colorize("&7This demonstrates data"),
                ColorSystem.colorize("&7retrieval and display."),
                "",
                ColorSystem.colorize("&aClick to activate")
            ));
            infoButton.setItemMeta(infoMeta);
        }
        menu.addItem(14, infoButton, (p, slot, item, action) -> {
            p.sendMessage(ColorSystem.colorize("&b=== Player Information ==="));
            p.sendMessage(ColorSystem.colorize("&bName: &7" + p.getName()));
            p.sendMessage(ColorSystem.colorize("&bHealth: &7" + p.getHealth() + "/" + p.getMaxHealth()));
            p.sendMessage(ColorSystem.colorize("&bFood Level: &7" + p.getFoodLevel()));
            p.sendMessage(ColorSystem.colorize("&bLevel: &7" + p.getLevel()));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
            return false; // Don't close the menu
        });
        
        // Toggle Button: Example of a stateful button
        ItemStack toggleButton = new ItemStack(Material.LEVER);
        ItemMeta toggleMeta = toggleButton.getItemMeta();
        if (toggleMeta != null) {
            toggleMeta.setDisplayName(ColorSystem.colorize("&7Toggle Example"));
            toggleMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7This demonstrates a toggle button"),
                ColorSystem.colorize("&7with dynamic state changes."),
                ColorSystem.colorize("&7State: &aON"),
                "",
                ColorSystem.colorize("&aClick to toggle")
            ));
            toggleButton.setItemMeta(toggleMeta);
        }
        menu.addItem(16, toggleButton, (p, slot, item, action) -> {
            try {
                // Simple toggle demonstration with null checks
                ItemStack currentItem = menu.getItemInSlot(slot);
                boolean isOn = false;
                
                // Safe null checks
                if (currentItem != null && currentItem.hasItemMeta()) {
                    ItemMeta meta = currentItem.getItemMeta();
                    if (meta != null && meta.hasLore()) {
                        List<String> lore = meta.getLore();
                        if (lore != null && lore.size() > 3) {
                            isOn = lore.get(3).contains("ON");
                        }
                    }
                }
                
                ItemStack newToggleButton = new ItemStack(Material.LEVER);
                ItemMeta newMeta = newToggleButton.getItemMeta();
                if (newMeta != null) {
                    newMeta.setDisplayName(ColorSystem.colorize("&7Toggle Example"));
                    newMeta.setLore(java.util.Arrays.asList(
                        "",
                        ColorSystem.colorize("&7This demonstrates a toggle button"),
                        ColorSystem.colorize("&7with dynamic state changes."),
                        ColorSystem.colorize("&7State: " + (isOn ? "&cOFF" : "&aON")),
                        "",
                        ColorSystem.colorize("&aClick to toggle")
                    ));
                    newToggleButton.setItemMeta(newMeta);
                }
                
                menu.replaceExistingItem(slot, newToggleButton);
                p.sendMessage(ColorSystem.colorize("&7Toggle switched to: " + (isOn ? "&cOFF" : "&aON")));
                p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 1.0f, 1.0f);
            } catch (Exception e) {
                p.sendMessage(ColorSystem.colorize("&cError with toggle button: " + e.getMessage()));
            }
            return false; // Don't close the menu
        });
    }

    /**
     * Adds navigation and utility buttons to the menu.
     * 
     * @param menu The ChestMenu to add buttons to
     * @param player The player who will interact with the buttons
     */
    private void addNavigationButtons(@Nonnull ChestMenu menu, @Nonnull Player player) {
        // Close button
        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();
        if (closeMeta != null) {
            closeMeta.setDisplayName(ColorSystem.colorize("&cClose"));
            closeMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7Click to close this menu"),
                ColorSystem.colorize("&7and return to the game."),
                "",
                ColorSystem.colorize("&cClick to close")
            ));
            closeButton.setItemMeta(closeMeta);
        }
        menu.addItem(22, closeButton, (p, slot, item, action) -> {
            p.closeInventory();
            p.sendMessage(ColorSystem.colorize("&cInventory closed!"));
            p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.0f, 1.0f);
            return false;
        });
        
        // Help button
        ItemStack helpButton = new ItemStack(Material.BOOK);
        ItemMeta helpMeta = helpButton.getItemMeta();
        if (helpMeta != null) {
            helpMeta.setDisplayName(ColorSystem.colorize("&7Help"));
            helpMeta.setLore(java.util.Arrays.asList(
                "",
                ColorSystem.colorize("&7This is an example inventory"),
                ColorSystem.colorize("&7demonstrating system features:"),
                "",
                ColorSystem.colorize("&b• Custom GUI creation"),
                ColorSystem.colorize("&b• Interactive buttons"),
                ColorSystem.colorize("&b• Click handlers"),
                ColorSystem.colorize("&b• Menu management"),
                ColorSystem.colorize("&b• Dynamic content updates"),
                "",
                ColorSystem.colorize("&aPerfect for addon developers!")
            ));
            helpButton.setItemMeta(helpMeta);
        }
        menu.addItem(18, helpButton, ChestMenuUtils.getEmptyClickHandler());
    }
}