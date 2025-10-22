package com.example.slimefunaddon.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
// Enchantable is now a property of SlimefunItem, not a separate interface
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedEnchantment;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * A powerful pickaxe made from Example Ingots
 * Features:
 * - Increased durability
 * - Built-in enchantments
 * - Special mining abilities
 */
public class ExamplePickaxe extends SlimefunItem implements DamageableItem {

    public ExamplePickaxe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        addItemHandler(onToolUse());
    }

    private ToolUseHandler onToolUse() {
        return new ToolUseHandler() {
            @Override
            public void onToolUse(BlockBreakEvent event, ItemStack tool, int fortune, List<ItemStack> drops) {
                Player player = event.getPlayer();
                Block block = event.getBlock();
                
                // Check if the tool is broken
                if (isDamageable() && tool.getType().isAir()) {
                    return;
                }
                
                // Apply special mining effects
                if (isPickaxeBlock(block.getType())) {
                    // Chance for bonus drops
                    if (Math.random() < 0.15) { // 15% chance
                        // Add bonus drop
                        ItemStack bonus = new ItemStack(block.getType(), 1);
                        drops.add(bonus);
                        
                        player.sendMessage("§6✦ §eBonusový drop!");
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.5f);
                    }
                    
                    // Damage the tool
                    if (isDamageable()) {
                        damageItem(player, tool);
                    }
                }
            }
        };
    }

    /**
     * Check if the block type can be mined with a pickaxe
     */
    private boolean isPickaxeBlock(Material material) {
        return material.name().contains("ORE") || 
               material.name().contains("STONE") ||
               material == Material.COBBLESTONE ||
               material == Material.NETHERRACK ||
               material == Material.END_STONE ||
               material == Material.OBSIDIAN;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    // DamageableItem interface provides default damageItem implementation

    /**
     * Get the maximum durability of this tool
     */
    private int getMaxDurability() {
        return 2500; // Higher than diamond pickaxe
    }

    // Enchantable is now a property of SlimefunItem, not separate methods

    // Enchantments are now added in the static block in ExampleItemStacks
}