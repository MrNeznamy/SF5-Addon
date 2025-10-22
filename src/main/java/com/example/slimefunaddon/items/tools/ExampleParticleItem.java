package com.example.slimefunaddon.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Example item that demonstrates particle effect creation and management.
 * This item allows players to create various particle effects at target locations.
 * 
 * Features:
 * - Right-click to create particle effects
 * - Cycles through different particle types
 * - Demonstrates particle system integration
 * - Shows animated particle effects
 */
public class ExampleParticleItem extends SlimefunItem {

    private static final Map<UUID, Integer> particleCounter = new HashMap<>();
    private static final Particle[] EXAMPLE_PARTICLES = {
        Particle.FLAME, Particle.HEART, Particle.HAPPY_VILLAGER, 
        Particle.WITCH, Particle.ENCHANT, Particle.PORTAL,
        Particle.DUST, Particle.FIREWORK, Particle.CLOUD
    };

    private final Plugin plugin;

    /**
     * Constructor for the ExampleParticleItem
     * 
     * @param itemGroup The item group this item belongs to
     * @param item The SlimefunItemStack representing this item
     * @param recipeType The recipe type for crafting this item
     * @param recipe The recipe array for crafting this item
     * @param plugin The plugin instance for scheduling tasks
     */
    public ExampleParticleItem(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, 
                              @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe, @Nonnull Plugin plugin) {
        super(itemGroup, item, recipeType, recipe);
        this.plugin = plugin;
        
        // Add the ItemUseHandler for right-click functionality
        addItemHandler(getItemUseHandler());
    }

    /**
     * Creates and returns the ItemUseHandler for this item.
     * This handler is triggered when a player right-clicks with the item.
     * 
     * @return ItemUseHandler that manages particle effect creation
     */
    @Nonnull
    private ItemUseHandler getItemUseHandler() {
        return e -> {
            e.cancel();
            
            Player player = e.getPlayer();
            Block targetBlock = player.getTargetBlock(null, 10);
            
            if (targetBlock.getType() == Material.AIR) {
                player.sendMessage(ColorSystem.colorize("&cYou must target a block!"));
                return;
            }
            
            if (player.isSneaking()) {
                // Create animated particle effect
                createAnimatedParticleEffect(player, targetBlock.getLocation());
            } else {
                // Create simple particle effect
                createSimpleParticleEffect(player, targetBlock.getLocation());
            }
        };
    }

    /**
     * Creates a simple particle effect at the specified location
     * 
     * @param player The player creating the effect
     * @param blockLocation The location where the effect should be created
     */
    private void createSimpleParticleEffect(@Nonnull Player player, @Nonnull Location blockLocation) {
        // Get or initialize counter for this player
        int count = particleCounter.getOrDefault(player.getUniqueId(), 0);
        Particle particleType = EXAMPLE_PARTICLES[count % EXAMPLE_PARTICLES.length];
        particleCounter.put(player.getUniqueId(), count + 1);
        
        // Create effect location above the target block
        Location effectLocation = blockLocation.clone().add(0.5, 1.5, 0.5);
        
        try {
            // Create particle effect based on type
            switch (particleType) {
                case DUST:
                    // Red dust particles with custom color
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 100, 100), 2.0f);
                    player.getWorld().spawnParticle(particleType, effectLocation, 20, 0.5, 0.5, 0.5, 0, dustOptions);
                    break;
                    
                case FIREWORK:
                    // Firework sparks with velocity
                    player.getWorld().spawnParticle(particleType, effectLocation, 30, 0.3, 0.3, 0.3, 0.1);
                    break;
                    
                case WITCH:
                    // Witch spell particles in a circle
                    for (int i = 0; i < 16; i++) {
                        double angle = 2 * Math.PI * i / 16;
                        double x = Math.cos(angle) * 1.5;
                        double z = Math.sin(angle) * 1.5;
                        Location particleLocation = effectLocation.clone().add(x, 0, z);
                        player.getWorld().spawnParticle(particleType, particleLocation, 1, 0, 0, 0, 0);
                    }
                    break;
                    
                default:
                    // Standard particle effect
                    player.getWorld().spawnParticle(particleType, effectLocation, 15, 0.5, 0.5, 0.5, 0.05);
                    break;
            }
            
            player.sendMessage(ColorSystem.colorize("&aCreated " + particleType.name().toLowerCase() + " effect!"));
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.5f, 1.0f);
            
        } catch (Exception ex) {
            player.sendMessage(ColorSystem.colorize("&cError creating particle effect: " + ex.getMessage()));
        }
    }

    /**
     * Creates an animated particle effect that runs over time
     * 
     * @param player The player creating the effect
     * @param blockLocation The location where the effect should be created
     */
    private void createAnimatedParticleEffect(@Nonnull Player player, @Nonnull Location blockLocation) {
        Location effectLocation = blockLocation.clone().add(0.5, 1.0, 0.5);
        
        player.sendMessage(ColorSystem.colorize("&bCreating animated particle effect!"));
        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0f, 1.0f);
        
        // Create a spiral animation
        new BukkitRunnable() {
            private int ticks = 0;
            private final int maxTicks = 100; // 5 seconds
            
            @Override
            public void run() {
                if (ticks >= maxTicks) {
                    // Final burst effect
                player.getWorld().spawnParticle(Particle.FIREWORK, effectLocation.clone().add(0, 2, 0), 50, 1.0, 1.0, 1.0, 0.2);
                player.playSound(effectLocation, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.0f);
                this.cancel();
                return;
            }
            
            // Create spiral effect
            double angle = ticks * 0.3;
            double radius = 1.5;
            double height = ticks * 0.05;
            
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            
            Location particleLocation = effectLocation.clone().add(x, height, z);
            
            // Alternate between different particles
            Particle particle = (ticks % 10 < 5) ? Particle.ENCHANT : Particle.PORTAL;
            player.getWorld().spawnParticle(particle, particleLocation, 2, 0.1, 0.1, 0.1, 0);
            
            // Add some random sparkles
            if (ticks % 5 == 0) {
                Location sparkleLocation = effectLocation.clone().add(
                    (Math.random() - 0.5) * 3,
                    Math.random() * 3,
                    (Math.random() - 0.5) * 3
                );
                player.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, sparkleLocation, 1, 0, 0, 0, 0);
            }
                
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates a custom particle pattern (example: heart shape)
     * 
     * @param player The player creating the effect
     * @param centerLocation The center location for the pattern
     */
    public void createHeartPattern(@Nonnull Player player, @Nonnull Location centerLocation) {
        new BukkitRunnable() {
            private int step = 0;
            private final int maxSteps = 50;
            
            @Override
            public void run() {
                if (step >= maxSteps) {
                    this.cancel();
                    return;
                }
                
                // Heart equation: x = 16sin³(t), y = 13cos(t) - 5cos(2t) - 2cos(3t) - cos(4t)
                double t = step * 2 * Math.PI / maxSteps;
                double x = 0.3 * Math.pow(Math.sin(t), 3);
                double y = 0.2 * (13 * Math.cos(t) - 5 * Math.cos(2*t) - 2 * Math.cos(3*t) - Math.cos(4*t)) / 13;
                
                Location heartLocation = centerLocation.clone().add(x, y + 1, 0);
                player.getWorld().spawnParticle(Particle.HEART, heartLocation, 1, 0, 0, 0, 0);
                
                step++;
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    /**
     * Resets the particle counter for a specific player
     * This can be useful for cleanup or testing purposes
     * 
     * @param playerUUID The UUID of the player to reset
     */
    public static void resetPlayerCounter(@Nonnull UUID playerUUID) {
        particleCounter.remove(playerUUID);
    }

    /**
     * Gets the current particle effect count for a player
     * 
     * @param playerUUID The UUID of the player
     * @return The number of particle effects created by this player
     */
    public static int getPlayerParticleCount(@Nonnull UUID playerUUID) {
        return particleCounter.getOrDefault(playerUUID, 0);
    }
}