package com.example.slimefunaddon.items.machines;

import com.example.slimefunaddon.items.ExampleItemStacks;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.OutputChest;
import eu.mrneznamy.slimefun5.items.CustomItemStack;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * A multiblock machine that crushes ores into dust
 * Structure:
 * [Fence] [Dispenser] [Fence]
 * [Fence] [Crafting Table] [Fence]
 * [Fence] [Fence] [Fence]
 */
public class ExampleCrusher extends MultiBlockMachine {

    public ExampleCrusher(ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe) {
        super(itemGroup, item, recipe, BlockFace.SELF);
    }

    @Override
    public void onInteract(Player player, Block block) {
        // Get the crafting table (center block)
        Block craftingTable = block.getRelative(BlockFace.DOWN);
        
        if (craftingTable.getType() != Material.CRAFTING_TABLE) {
            Slimefun.getLocalization().sendMessage(player, "machines.wrong-item", true);
            return;
        }

        // Get the dispenser inventory
        Block dispenser = block;
        if (dispenser.getType() != Material.DISPENSER) {
            Slimefun.getLocalization().sendMessage(player, "machines.wrong-item", true);
            return;
        }

        if (!(dispenser.getState() instanceof org.bukkit.block.Dispenser)) {
            return;
        }
        
        Inventory inv = ((org.bukkit.block.Dispenser) dispenser.getState()).getInventory();

        // Process items
        List<ItemStack> inputs = new ArrayList<>();
        for (ItemStack item : inv.getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                inputs.add(item.clone());
            }
        }

        if (inputs.isEmpty()) {
            Slimefun.getLocalization().sendMessage(player, "machines.no-items", true);
            return;
        }

        // Find output
        ItemStack output = getOutput(inputs);
        if (output == null) {
            Slimefun.getLocalization().sendMessage(player, "machines.unknown-material", true);
            return;
        }

        // Try to output the item - simplified for example
        // Remove input items
        for (ItemStack input : inputs) {
            inv.removeItem(input);
        }
        
        // Give output to player (simplified)
        player.getInventory().addItem(output);
        
        // Play sound effect
        player.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
        
        Slimefun.getLocalization().sendMessage(player, "machines.crushed-item", true);
    }

    /**
     * Get the output item for the given inputs
     * @param inputs List of input items
     * @return The output item or null if no recipe matches
     */
    private ItemStack getOutput(List<ItemStack> inputs) {
        // Check for single item recipes
        for (ItemStack input : inputs) {
            if (input == null || input.getType() == Material.AIR) {
                continue;
            }

            // Example Ore -> Example Dust (2x)
            if (input.isSimilar(ExampleItemStacks.EXAMPLE_ORE.item())) {
                ItemStack dust = ExampleItemStacks.EXAMPLE_DUST.item();
                dust.setAmount(2);
                return dust;
            }
            
            // Iron Ore -> Iron Dust (2x)
            if (input.getType() == Material.IRON_ORE) {
                return new ItemStack(Material.GUNPOWDER, 2); // Using gunpowder as iron dust
            }
            
            // Gold Ore -> Gold Dust (2x)
            if (input.getType() == Material.GOLD_ORE) {
                return new ItemStack(Material.GLOWSTONE_DUST, 2); // Using glowstone dust as gold dust
            }
            
            // Coal Ore -> Coal (3x)
            if (input.getType() == Material.COAL_ORE) {
                return new ItemStack(Material.COAL, 3);
            }
            
            // Diamond Ore -> Diamond (1x) + Diamond Dust (1x)
            if (input.getType() == Material.DIAMOND_ORE) {
                return new ItemStack(Material.DIAMOND, 1);
            }
            
            // Cobblestone -> Gravel
            if (input.getType() == Material.COBBLESTONE) {
                return new ItemStack(Material.GRAVEL, 1);
            }
            
            // Gravel -> Sand
            if (input.getType() == Material.GRAVEL) {
                return new ItemStack(Material.SAND, 1);
            }
        }

        return null; // No matching recipe
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> recipes = new ArrayList<>();
        
        // Example Ore -> Example Dust
        recipes.add(ExampleItemStacks.EXAMPLE_ORE.item());
        ItemStack dust = ExampleItemStacks.EXAMPLE_DUST.item();
        dust.setAmount(2);
        recipes.add(dust);
        
        // Iron Ore -> Iron Dust
        recipes.add(new ItemStack(Material.IRON_ORE));
        recipes.add(new CustomItemStack(Material.GUNPOWDER, ColorSystem.colorize("&7Iron Dust"), ColorSystem.colorize("&7x2")));
        
        // Gold Ore -> Gold Dust
        recipes.add(new ItemStack(Material.GOLD_ORE));
        recipes.add(new CustomItemStack(Material.GLOWSTONE_DUST, ColorSystem.colorize("&eGold Dust"), ColorSystem.colorize("&ex2")));
        
        // Coal Ore -> Coal
        recipes.add(new ItemStack(Material.COAL_ORE));
        recipes.add(new ItemStack(Material.COAL, 3));
        
        // Cobblestone -> Gravel
        recipes.add(new ItemStack(Material.COBBLESTONE));
        recipes.add(new ItemStack(Material.GRAVEL));
        
        // Gravel -> Sand
        recipes.add(new ItemStack(Material.GRAVEL));
        recipes.add(new ItemStack(Material.SAND));
        
        return recipes;
    }
}