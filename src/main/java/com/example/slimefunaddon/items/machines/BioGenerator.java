package com.example.slimefunaddon.items.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * A generator that converts organic materials into energy
 * Features:
 * - Accepts various plant-based fuels
 * - Different burn times for different materials
 * - Environmentally friendly energy generation
 */
public class BioGenerator extends AGenerator implements RecipeDisplayItem {

    public BioGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    // getMachineIdentifier is not required for AGenerator

    @Override
    public int getCapacity() {
        return 256; // Energy capacity
    }

    @Override
    public int getEnergyProduction() {
        return 24; // Energy per tick when active
    }

    @Override
    protected void registerDefaultFuelTypes() {
        // Basic plant materials
        registerFuel(new MachineFuel(4, new ItemStack(Material.WHEAT))); // 4 seconds
        registerFuel(new MachineFuel(4, new ItemStack(Material.CARROT)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.POTATO)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.BEETROOT)));
        
        // Seeds and saplings
        registerFuel(new MachineFuel(2, new ItemStack(Material.WHEAT_SEEDS)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.BEETROOT_SEEDS)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.PUMPKIN_SEEDS)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.MELON_SEEDS)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.OAK_SAPLING)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.BIRCH_SAPLING)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.SPRUCE_SAPLING)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.JUNGLE_SAPLING)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.ACACIA_SAPLING)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.DARK_OAK_SAPLING)));
        
        // Fruits and vegetables
        registerFuel(new MachineFuel(8, new ItemStack(Material.APPLE)));
        registerFuel(new MachineFuel(12, new ItemStack(Material.PUMPKIN)));
        registerFuel(new MachineFuel(12, new ItemStack(Material.MELON)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.SWEET_BERRIES)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.GLOW_BERRIES)));
        
        // Flowers and plants
        registerFuel(new MachineFuel(3, new ItemStack(Material.DANDELION)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.POPPY)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.BLUE_ORCHID)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.ALLIUM)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.AZURE_BLUET)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.OXEYE_DAISY)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.CORNFLOWER)));
        registerFuel(new MachineFuel(3, new ItemStack(Material.LILY_OF_THE_VALLEY)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.SUNFLOWER)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.ROSE_BUSH)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.PEONY)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.LILAC)));
        
        // Grass and ferns
        registerFuel(new MachineFuel(1, new ItemStack(Material.FERN)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.TALL_GRASS)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.LARGE_FERN)));
        
        // Kelp and seagrass
        registerFuel(new MachineFuel(2, new ItemStack(Material.KELP)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.SEAGRASS)));
        
        // Mushrooms
        registerFuel(new MachineFuel(4, new ItemStack(Material.RED_MUSHROOM)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.BROWN_MUSHROOM)));
        registerFuel(new MachineFuel(8, new ItemStack(Material.RED_MUSHROOM_BLOCK)));
        registerFuel(new MachineFuel(8, new ItemStack(Material.BROWN_MUSHROOM_BLOCK)));
        
        // Leaves (lower efficiency)
        registerFuel(new MachineFuel(1, new ItemStack(Material.OAK_LEAVES)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.BIRCH_LEAVES)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.SPRUCE_LEAVES)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.JUNGLE_LEAVES)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.ACACIA_LEAVES)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.DARK_OAK_LEAVES)));
        
        // Processed plant materials
        registerFuel(new MachineFuel(6, new ItemStack(Material.BREAD)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.COOKIE)));
        registerFuel(new MachineFuel(8, new ItemStack(Material.CAKE)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.PUMPKIN_PIE)));
        
        // Bamboo
        registerFuel(new MachineFuel(2, new ItemStack(Material.BAMBOO)));
        
        // Cactus
        registerFuel(new MachineFuel(3, new ItemStack(Material.CACTUS)));
        
        // Sugar cane
        registerFuel(new MachineFuel(2, new ItemStack(Material.SUGAR_CANE)));
        registerFuel(new MachineFuel(1, new ItemStack(Material.SUGAR)));
        
        // Hay bales (very efficient)
        registerFuel(new MachineFuel(20, new ItemStack(Material.HAY_BLOCK)));
        
        // Dried kelp blocks
        registerFuel(new MachineFuel(16, new ItemStack(Material.DRIED_KELP_BLOCK)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.DRIED_KELP)));
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>();
        
        // Show fuel types and their burn times
        List<MachineFuel> fuelTypes = new ArrayList<>(getFuelTypes());
        
        for (MachineFuel fuel : fuelTypes) {
            ItemStack fuelItem = fuel.getInput().clone();
            
            // Add burn time to the lore
            if (fuelItem.getItemMeta() != null) {
                List<String> lore = fuelItem.getItemMeta().getLore();
                if (lore == null) {
                    lore = new ArrayList<>();
                }
                lore.add("§7Burn Time: §e" + fuel.getTicks() + " seconds");
                lore.add("§7Energy: §e" + (fuel.getTicks() * getEnergyProduction()) + " J");
                
                fuelItem.getItemMeta().setLore(lore);
            }
            
            displayRecipes.add(fuelItem);
            displayRecipes.add(new ItemStack(Material.REDSTONE)); // Represents energy output
        }
        
        return displayRecipes;
    }
}