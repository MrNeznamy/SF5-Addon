package com.example.slimefunaddon.items.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * An advanced solar panel that generates more energy than standard panels
 * Features:
 * - Higher energy output during day
 * - Reduced but still functional output during night
 * - Weather-dependent generation
 * - Dimension-aware functionality
 */
public class AdvancedSolarPanel extends SlimefunItem implements EnergyNetProvider {

    private final int dayEnergy;
    private final int nightEnergy;
    private final int capacity;

    public AdvancedSolarPanel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        this.dayEnergy = 32; // Energy per tick during day
        this.nightEnergy = 8; // Energy per tick during night
        this.capacity = 512; // Energy storage capacity
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getGeneratedOutput(Location location, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config config) {
        World world = location.getWorld();
        
        if (world == null) {
            return 0;
        }

        // Check if it's in the overworld (solar panels work best there)
        if (world.getEnvironment() != World.Environment.NORMAL) {
            // Reduced efficiency in Nether/End
            return nightEnergy / 2;
        }

        // Check if the block above is air (not blocked)
        Block block = location.getBlock();
        Block above = block.getRelative(0, 1, 0);
        if (above.getType() != Material.AIR) {
            return 0; // Blocked by something above
        }

        // Check time and weather
        long time = world.getTime();
        boolean isDay = isDaytime(time);
        boolean isStorming = world.hasStorm();

        if (isDay && !isStorming) {
            // Perfect conditions - full power
            return dayEnergy;
        } else if (isDay && isStorming) {
            // Day but stormy - reduced power
            return dayEnergy / 2;
        } else {
            // Night time - minimal power (moonlight/starlight)
            return nightEnergy;
        }
    }

    /**
     * Check if it's daytime
     * @param time The world time
     * @return true if it's daytime
     */
    private boolean isDaytime(long time) {
        // Minecraft day cycle: 0-12000 is day, 12000-24000 is night
        return time >= 0 && time < 12000;
    }

    /**
     * Get the light level at the panel location
     * @param block The solar panel block
     * @return The light level (0-15)
     */
    private int getLightLevel(Block block) {
        return block.getLightFromSky();
    }

    /**
     * Check if the solar panel has a clear view of the sky
     * @param block The solar panel block
     * @return true if there's a clear view to the sky
     */
    private boolean hasClearSky(Block block) {
        // Check blocks above up to build height
        for (int y = block.getY() + 1; y < block.getWorld().getMaxHeight(); y++) {
            Block above = block.getWorld().getBlockAt(block.getX(), y, block.getZ());
            if (above.getType() != Material.AIR && 
                above.getType() != Material.GLASS && 
                above.getType() != Material.GLASS_PANE) {
                return false;
            }
        }
        return true;
    }
}