package com.example.slimefunaddon.items;

import com.example.slimefunaddon.ExampleAddon;
import com.example.slimefunaddon.items.machines.AdvancedSolarPanel;
import com.example.slimefunaddon.items.machines.BioGenerator;
import com.example.slimefunaddon.items.machines.ElectricProcessor;
import com.example.slimefunaddon.items.machines.ExampleCrusher;
import com.example.slimefunaddon.items.tools.ExamplePickaxe;
import com.example.slimefunaddon.items.tools.ExampleInventoryItem;
import com.example.slimefunaddon.items.tools.ExampleEntityItem;
import com.example.slimefunaddon.items.tools.ExampleParticleItem;
import com.example.slimefunaddon.items.weapons.ExampleSword;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Main class for registering all addon items and their recipes
 */
public class ExampleItems {

    // Basic items
    public static SlimefunItem EXAMPLE_ORE;
    public static SlimefunItem EXAMPLE_INGOT;
    public static SlimefunItem EXAMPLE_DUST;
    public static SlimefunItem EXAMPLE_REINFORCED_PLATE;
    public static SlimefunItem ENERGY_CORE;
    public static SlimefunItem EXAMPLE_CIRCUIT_BOARD;

    // Machines
    public static SlimefunItem EXAMPLE_CRUSHER;
    public static SlimefunItem ELECTRIC_PROCESSOR;

    // Energy
    public static SlimefunItem ADVANCED_SOLAR_PANEL;
    public static SlimefunItem EXAMPLE_BIO_GENERATOR;

    // Tools and Weapons
    public static SlimefunItem EXAMPLE_PICKAXE;
    public static SlimefunItem EXAMPLE_SWORD;
    public static SlimefunItem EXAMPLE_INVENTORY_ITEM;
    
    // Special Items
    public static SlimefunItem EXAMPLE_ENTITY_ITEM;
    public static SlimefunItem EXAMPLE_PARTICLE_ITEM;

    /**
     * Setup and register all items
     * @param plugin The plugin instance
     */
    public static void setup(ExampleAddon plugin) {
        plugin.logInfo("Registering addon items...");

        // Register basic resources first
        registerResources(plugin);
        
        // Register machines
        registerMachines(plugin);
        
        // Register energy items
        registerEnergyItems(plugin);
        
        // Register tools and weapons
        registerToolsAndWeapons(plugin);
        
        // Register special items
        registerSpecialItems(plugin);

        plugin.logInfo("All addon items registered successfully!");
    }

    private static void registerResources(ExampleAddon plugin) {
        // Example Ore - found naturally or created
        EXAMPLE_ORE = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.EXAMPLE_ORE,
            RecipeType.GEO_MINER,
            new ItemStack[9] // Empty recipe - found by GEO Miner
        );
        EXAMPLE_ORE.register(plugin);

        // Example Dust - created by crushing ore
        EXAMPLE_DUST = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.EXAMPLE_DUST,
            RecipeType.GRIND_STONE,
            new ItemStack[] {
                ExampleItemStacks.EXAMPLE_ORE.item(), null, null,
                null, null, null,
                null, null, null
            }
        );
        EXAMPLE_DUST.register(plugin);

        // Example Ingot - smelted from ore or dust
        EXAMPLE_INGOT = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.EXAMPLE_INGOT,
            RecipeType.SMELTERY,
            new ItemStack[] {
                ExampleItemStacks.EXAMPLE_ORE.item(), null, null,
                null, null, null,
                null, null, null
            }
        );
        EXAMPLE_INGOT.register(plugin);

        // Reinforced Plate - crafted from ingots
        EXAMPLE_REINFORCED_PLATE = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.EXAMPLE_REINFORCED_PLATE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item(),
                ExampleItemStacks.EXAMPLE_INGOT.item(), new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE), ExampleItemStacks.EXAMPLE_INGOT.item(),
                ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item()
            }
        );
        EXAMPLE_REINFORCED_PLATE.register(plugin);

        // Circuit Board - electronic component
        EXAMPLE_CIRCUIT_BOARD = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.COPPER_INGOT),
                new ItemStack(Material.REDSTONE), new ItemStack(Material.PAPER), new ItemStack(Material.REDSTONE),
                new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.COPPER_INGOT)
            }
        );
        EXAMPLE_CIRCUIT_BOARD.register(plugin);

        // Energy Core - advanced component
        ENERGY_CORE = new SlimefunItem(
            ExampleItemGroups.RESOURCES,
            ExampleItemStacks.ENERGY_CORE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), ExampleItemStacks.EXAMPLE_INGOT.item(),
                ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.DIAMOND), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(),
                ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), ExampleItemStacks.EXAMPLE_INGOT.item()
            }
        );
        ENERGY_CORE.register(plugin);
    }

    private static void registerMachines(ExampleAddon plugin) {
        // Example Crusher - multiblock machine
        EXAMPLE_CRUSHER = new ExampleCrusher(
            ExampleItemGroups.MACHINES,
            ExampleItemStacks.EXAMPLE_CRUSHER,
            new ItemStack[] {
                new ItemStack(Material.IRON_BARS), new ItemStack(Material.DISPENSER), new ItemStack(Material.IRON_BARS),
                new ItemStack(Material.IRON_BARS), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.IRON_BARS),
                new ItemStack(Material.IRON_BARS), new ItemStack(Material.IRON_BARS), new ItemStack(Material.IRON_BARS)
            }
        );
        EXAMPLE_CRUSHER.register(plugin);

        // Electric Processor - electric consumer machine
        ELECTRIC_PROCESSOR = new ElectricProcessor(
            ExampleItemGroups.MACHINES,
            ExampleItemStacks.ELECTRIC_PROCESSOR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(),
                ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.FURNACE), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(),
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), new ItemStack(Material.PISTON), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()
            }
        );
        ELECTRIC_PROCESSOR.register(plugin);
    }

    private static void registerEnergyItems(ExampleAddon plugin) {
        // Advanced Solar Panel
        ADVANCED_SOLAR_PANEL = new AdvancedSolarPanel(
            ExampleItemGroups.ENERGY,
            ExampleItemStacks.ADVANCED_SOLAR_PANEL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS),
                ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.DAYLIGHT_DETECTOR), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(),
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), ExampleItemStacks.ENERGY_CORE.item(), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()
            }
        );
        ADVANCED_SOLAR_PANEL.register(plugin);

        // Bio Generator
        EXAMPLE_BIO_GENERATOR = new BioGenerator(
            ExampleItemGroups.ENERGY,
            ExampleItemStacks.EXAMPLE_BIO_GENERATOR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.OAK_LOG), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.OAK_LOG),
                new ItemStack(Material.OAK_LOG), new ItemStack(Material.FURNACE), new ItemStack(Material.OAK_LOG),
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), new ItemStack(Material.PISTON), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()
            }
        );
        EXAMPLE_BIO_GENERATOR.register(plugin);
    }

    private static void registerToolsAndWeapons(ExampleAddon plugin) {
        // Example Pickaxe
        EXAMPLE_PICKAXE = new ExamplePickaxe(
            ExampleItemGroups.TOOLS,
            ExampleItemStacks.EXAMPLE_PICKAXE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_INGOT.item(),
                null, new ItemStack(Material.STICK), null,
                null, new ItemStack(Material.STICK), null
            }
        );
        EXAMPLE_PICKAXE.register(plugin);

        // Example Sword
        EXAMPLE_SWORD = new ExampleSword(
            ExampleItemGroups.TOOLS,
            ExampleItemStacks.EXAMPLE_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, ExampleItemStacks.EXAMPLE_INGOT.item(), null,
                null, ExampleItemStacks.EXAMPLE_INGOT.item(), null,
                null, new ItemStack(Material.STICK), null
            }
        );
        EXAMPLE_SWORD.register(plugin);

        // Example Inventory Item - demonstrates custom inventory system
        EXAMPLE_INVENTORY_ITEM = new ExampleInventoryItem(
            ExampleItemGroups.TOOLS,
            ExampleItemStacks.EXAMPLE_INVENTORY_ITEM,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.CHEST), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.REDSTONE), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.REDSTONE),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BOOK), new ItemStack(Material.IRON_INGOT)
            }
        );
        EXAMPLE_INVENTORY_ITEM.register(plugin);
    }

    private static void registerSpecialItems(ExampleAddon plugin) {
        // Example Entity Item - demonstrates entity management
        EXAMPLE_ENTITY_ITEM = new ExampleEntityItem(
            ExampleItemGroups.TOOLS,
            ExampleItemStacks.EXAMPLE_ENTITY_ITEM,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.BONE), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.BONE),
                ExampleItemStacks.EXAMPLE_INGOT.item(), new ItemStack(Material.SPAWNER), ExampleItemStacks.EXAMPLE_INGOT.item(),
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), new ItemStack(Material.EGG), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()
            }
        );
        EXAMPLE_ENTITY_ITEM.register(plugin);

        // Example Particle Item - demonstrates particle effects
        EXAMPLE_PARTICLE_ITEM = new ExampleParticleItem(
            ExampleItemGroups.TOOLS,
            ExampleItemStacks.EXAMPLE_PARTICLE_ITEM,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.GUNPOWDER), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item(), new ItemStack(Material.GUNPOWDER),
                ExampleItemStacks.EXAMPLE_INGOT.item(), new ItemStack(Material.FIREWORK_STAR), ExampleItemStacks.EXAMPLE_INGOT.item(),
                ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item(), new ItemStack(Material.BLAZE_POWDER), ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()
            },
            plugin
        );
        EXAMPLE_PARTICLE_ITEM.register(plugin);
    }
}