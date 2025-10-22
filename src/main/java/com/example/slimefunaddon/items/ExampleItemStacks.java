package com.example.slimefunaddon.items;

import eu.mrneznamy.slimefun5.items.CustomItemStack;
import eu.mrneznamy.utils.ColorSystem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import org.bukkit.Material;

/**
 * Contains all SlimefunItemStacks for the Example Addon
 */
public class ExampleItemStacks {

    // Basic Resources
    public static final SlimefunItemStack EXAMPLE_ORE = new SlimefunItemStack(
        "EXAMPLE_ORE",
        Material.COBBLESTONE,
        ColorSystem.colorize("&bExample Ore"),
        "",
        ColorSystem.colorize("&7A mysterious ore found deep underground"),
        ColorSystem.colorize("&7Can be smelted into Example Ingots")
    );

    public static final SlimefunItemStack EXAMPLE_INGOT = new SlimefunItemStack(
        "EXAMPLE_INGOT",
        Material.IRON_INGOT,
        ColorSystem.colorize("&bExample Ingot"),
        "",
        ColorSystem.colorize("&7A refined ingot made from Example Ore"),
        ColorSystem.colorize("&7Used in advanced crafting recipes")
    );

    public static final SlimefunItemStack EXAMPLE_DUST = new SlimefunItemStack(
        "EXAMPLE_DUST",
        Material.GUNPOWDER,
        ColorSystem.colorize("&bExample Dust"),
        "",
        ColorSystem.colorize("&7Fine dust created by crushing Example Ore"),
        ColorSystem.colorize("&7Can be smelted more efficiently than ore")
    );

    public static final SlimefunItemStack EXAMPLE_REINFORCED_PLATE = new SlimefunItemStack(
        "EXAMPLE_REINFORCED_PLATE",
        Material.PAPER,
        ColorSystem.colorize("&bExample Reinforced Plate"),
        "",
        ColorSystem.colorize("&7A sturdy plate made from Example Ingots"),
        ColorSystem.colorize("&7Used in machine construction")
    );

    // Machines
    public static final SlimefunItemStack EXAMPLE_CRUSHER = new SlimefunItemStack(
        "EXAMPLE_CRUSHER",
        Material.DISPENSER,
        ColorSystem.colorize("&cExample Crusher"),
        "",
        ColorSystem.colorize("&7A multiblock machine that crushes ores"),
        ColorSystem.colorize("&7into dust for more efficient smelting"),
        "",
        ColorSystem.colorize("&bMultiblock Machine")
    );

    public static final SlimefunItemStack ELECTRIC_PROCESSOR = new SlimefunItemStack(
        "ELECTRIC_PROCESSOR",
        Material.FURNACE,
        ColorSystem.colorize("&bElectric Processor"),
        "",
        ColorSystem.colorize("&7An advanced electric machine that"),
        ColorSystem.colorize("&7processes materials efficiently"),
        "",
        ColorSystem.colorize("&7⚡ &b128 J Buffer"),
        ColorSystem.colorize("&7⚡ &b16 J/s")
    );

    // Energy
    public static final SlimefunItemStack ADVANCED_SOLAR_PANEL = new SlimefunItemStack(
        "ADVANCED_SOLAR_PANEL",
        Material.DAYLIGHT_DETECTOR,
        ColorSystem.colorize("&bAdvanced Solar Panel"),
        "",
        ColorSystem.colorize("&7An improved solar panel that generates"),
        ColorSystem.colorize("&7more energy than standard panels"),
        "",
        ColorSystem.colorize("&7⚡ &b512 J Buffer"),
        ColorSystem.colorize("&7⚡ &b32 J/s (Day)"),
        ColorSystem.colorize("&7⚡ &b8 J/s (Night)")
    );

    public static final SlimefunItemStack EXAMPLE_BIO_GENERATOR = new SlimefunItemStack(
        "EXAMPLE_BIO_GENERATOR",
        Material.BROWN_MUSHROOM_BLOCK,
        ColorSystem.colorize("&aExample Bio Generator"),
        "",
        ColorSystem.colorize("&7Converts organic materials into energy"),
        ColorSystem.colorize("&7Accepts various plant-based fuels"),
        "",
        ColorSystem.colorize("&7⚡ &b256 J Buffer"),
        ColorSystem.colorize("&7⚡ &b24 J/s")
    );

    // Tools
    public static final SlimefunItemStack EXAMPLE_PICKAXE = new SlimefunItemStack(
        "EXAMPLE_PICKAXE",
        Material.DIAMOND_PICKAXE,
        ColorSystem.colorize("&bExample Pickaxe"),
        "",
        ColorSystem.colorize("&7A powerful pickaxe with special abilities"),
        ColorSystem.colorize("&7Can break blocks in a 3x3 area"),
        ColorSystem.colorize("&7Has a chance to give extra drops"),
        "",
        ColorSystem.colorize("&7Durability: 2000"),
        ColorSystem.colorize("&7Enchantable: Yes")
    );

    public static final SlimefunItemStack EXAMPLE_SWORD = new SlimefunItemStack(
        "EXAMPLE_SWORD",
        Material.DIAMOND_SWORD,
        ColorSystem.colorize("&cExample Sword"),
        "",
        ColorSystem.colorize("&7A legendary sword with special powers"),
        ColorSystem.colorize("&7Sets enemies on fire"),
        ColorSystem.colorize("&7Has a chance for critical hits"),
        ColorSystem.colorize("&7Right-click for temporary buffs"),
        "",
        ColorSystem.colorize("&7Durability: 2000"),
        ColorSystem.colorize("&7Enchantable: Yes")
    );

    public static final SlimefunItemStack EXAMPLE_INVENTORY_ITEM = new SlimefunItemStack(
        "EXAMPLE_INVENTORY_ITEM",
        Material.CHEST,
        ColorSystem.colorize("&bExample Inventory Tool"),
        "",
        ColorSystem.colorize("&7A demonstration tool that showcases"),
        ColorSystem.colorize("&7the custom inventory system"),
        "",
        ColorSystem.colorize("&bRight-click to open custom GUI"),
        ColorSystem.colorize("&7• Interactive buttons"),
        ColorSystem.colorize("&7• Custom menu layouts"),
        ColorSystem.colorize("&7• Click handlers"),
        ColorSystem.colorize("&7• Perfect for addon developers!")
    );

    // Advanced Components
    public static final SlimefunItemStack ENERGY_CORE = new SlimefunItemStack(
        "ENERGY_CORE",
        Material.NETHER_STAR,
        ColorSystem.colorize("&bEnergy Core"),
        "",
        ColorSystem.colorize("&7A concentrated energy storage device"),
        ColorSystem.colorize("&7Used in advanced machine construction")
    );

    public static final SlimefunItemStack EXAMPLE_CIRCUIT_BOARD = new SlimefunItemStack(
        "EXAMPLE_CIRCUIT_BOARD",
        Material.ACTIVATOR_RAIL,
        ColorSystem.colorize("&aExample Circuit Board"),
        "",
        ColorSystem.colorize("&7An electronic component for machines"),
        ColorSystem.colorize("&7Enables advanced functionality")
    );

    // Special Items
    public static final SlimefunItemStack EXAMPLE_HOLOGRAM_ITEM = new SlimefunItemStack(
        "EXAMPLE_HOLOGRAM_ITEM",
        Material.BEACON,
        ColorSystem.colorize("&bHologram Creator"),
        "",
        ColorSystem.colorize("&7Creates and manages holograms"),
        ColorSystem.colorize("&7Right-click to create a hologram"),
        ColorSystem.colorize("&7Shift-right-click to remove holograms"),
        ColorSystem.colorize("&7Shows floating text at target location")
    );

    public static final SlimefunItemStack EXAMPLE_ENTITY_ITEM = new SlimefunItemStack(
        "EXAMPLE_ENTITY_ITEM",
        Material.SPAWNER,
        ColorSystem.colorize("&aEntity Manager"),
        "",
        ColorSystem.colorize("&7Manages entities and their properties"),
        ColorSystem.colorize("&7Right-click to spawn example entity"),
        ColorSystem.colorize("&7Shift-right-click to remove nearby entities"),
        ColorSystem.colorize("&7Demonstrates entity manipulation")
    );

    public static final SlimefunItemStack EXAMPLE_PARTICLE_ITEM = new SlimefunItemStack(
        "EXAMPLE_PARTICLE_ITEM",
        Material.FIREWORK_STAR,
        ColorSystem.colorize("&cParticle Generator"),
        "",
        ColorSystem.colorize("&7Creates various particle effects"),
        ColorSystem.colorize("&7Right-click to create particles"),
        ColorSystem.colorize("&7Cycles through different effects"),
        ColorSystem.colorize("&7Visual demonstration of particle system")
    );
}