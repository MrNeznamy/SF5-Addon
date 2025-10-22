package com.example.slimefunaddon.items.machines;

import com.example.slimefunaddon.items.ExampleItemStacks;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ElectricSmeltery;
import eu.mrneznamy.slimefun5.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * An electric machine that processes materials efficiently
 * Features:
 * - Processes ores into ingots with bonus output
 * - Processes dusts into ingots faster than smelting
 * - Creates advanced materials from basic ones
 */
public class ElectricProcessor extends AContainer implements RecipeDisplayItem {

    public ElectricProcessor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_PROCESSOR";
    }

    @Override
    public int getCapacity() {
        return 128; // Energy capacity
    }

    @Override
    public int getEnergyConsumption() {
        return 16; // Energy per tick
    }

    @Override
    public int getSpeed() {
        return 1; // Processing speed multiplier
    }

    @Override
    protected void registerDefaultRecipes() {
        // Example Dust -> Example Ingot (faster than smelting)
        registerRecipe(4, new ItemStack[]{ExampleItemStacks.EXAMPLE_DUST.item()}, new ItemStack[]{ExampleItemStacks.EXAMPLE_INGOT.item()});
        
        // Example Ore -> Example Ingot + bonus dust
        registerRecipe(8, new ItemStack[]{ExampleItemStacks.EXAMPLE_ORE.item()}, 
                      new ItemStack[]{ExampleItemStacks.EXAMPLE_INGOT.item(), ExampleItemStacks.EXAMPLE_DUST.item()});
        
        // Iron Ore -> Iron Ingot + bonus
        registerRecipe(6, new ItemStack[]{new ItemStack(Material.IRON_ORE)}, 
                      new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_NUGGET, 3)});
        
        // Gold Ore -> Gold Ingot + bonus
        registerRecipe(6, new ItemStack[]{new ItemStack(Material.GOLD_ORE)}, 
                      new ItemStack[]{new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_NUGGET, 3)});
        
        // Copper Ore -> Copper Ingot + bonus
        registerRecipe(6, new ItemStack[]{new ItemStack(Material.COPPER_ORE)}, 
                      new ItemStack[]{new ItemStack(Material.COPPER_INGOT, 2)});
        
        // Advanced processing: Create reinforced plates
        registerRecipe(12, new ItemStack[]{ExampleItemStacks.EXAMPLE_INGOT.item(), new ItemStack(Material.IRON_INGOT)}, 
                      new ItemStack[]{ExampleItemStacks.EXAMPLE_REINFORCED_PLATE.item()});
        
        // Create circuit boards
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.COPPER_INGOT), new ItemStack(Material.REDSTONE)}, 
                       new ItemStack[]{ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item()});
        
        // Advanced processing: Create energy cores
        registerRecipe(20, new ItemStack[]{ExampleItemStacks.EXAMPLE_INGOT.item(), new ItemStack(Material.DIAMOND), ExampleItemStacks.EXAMPLE_CIRCUIT_BOARD.item()}, 
                      new ItemStack[]{ExampleItemStacks.ENERGY_CORE.item()});
        
        // Raw materials processing
        registerRecipe(4, new ItemStack[]{new ItemStack(Material.RAW_IRON)}, 
                      new ItemStack[]{new ItemStack(Material.IRON_INGOT)});
        
        registerRecipe(4, new ItemStack[]{new ItemStack(Material.RAW_GOLD)}, 
                      new ItemStack[]{new ItemStack(Material.GOLD_INGOT)});
        
        registerRecipe(4, new ItemStack[]{new ItemStack(Material.RAW_COPPER)}, 
                      new ItemStack[]{new ItemStack(Material.COPPER_INGOT)});
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>();
        
        // Show all recipes in the Slimefun guide
        List<MachineRecipe> recipes = getMachineRecipes();
        
        for (MachineRecipe recipe : recipes) {
            // Add input items
            for (ItemStack input : recipe.getInput()) {
                displayRecipes.add(input);
            }
            
            // Add output items
            for (ItemStack output : recipe.getOutput()) {
                displayRecipes.add(output);
            }
        }
        
        return displayRecipes;
    }

    // Menu setup is handled by the parent AContainer class
}