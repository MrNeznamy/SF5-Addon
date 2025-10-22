package com.example.slimefunaddon.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Example item that demonstrates entity management and manipulation.
 * This item allows players to spawn and manage various entities.
 * 
 * Features:
 * - Right-click to spawn example entities
 * - Shift-right-click to remove nearby entities
 * - Demonstrates entity system integration
 * - Shows entity customization and effects
 */
public class ExampleEntityItem extends SlimefunItem {

    private static final Map<UUID, Integer> entityCounter = new HashMap<>();
    private static final double REMOVAL_RADIUS = 10.0;
    private static final EntityType[] EXAMPLE_ENTITIES = {
        EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, 
        EntityType.SHEEP, EntityType.COW, EntityType.CHICKEN
    };

    /**
     * Constructor for the ExampleEntityItem
     * 
     * @param itemGroup The item group this item belongs to
     * @param item The SlimefunItemStack representing this item
     * @param recipeType The recipe type for crafting this item
     * @param recipe The recipe array for crafting this item
     */
    public ExampleEntityItem(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, 
                            @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        // Add the ItemUseHandler for right-click functionality
        addItemHandler(getItemUseHandler());
    }

    /**
     * Creates and returns the ItemUseHandler for this item.
     * This handler is triggered when a player right-clicks with the item.
     * 
     * @return ItemUseHandler that manages entity spawning/removal
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
                // Remove nearby entities
                removeNearbyEntities(player, targetBlock.getLocation());
            } else {
                // Spawn new entity
                spawnExampleEntity(player, targetBlock.getLocation());
            }
        };
    }

    /**
     * Spawns a new example entity at the specified location
     * 
     * @param player The player spawning the entity
     * @param blockLocation The location where the entity should be spawned
     */
    private void spawnExampleEntity(@Nonnull Player player, @Nonnull Location blockLocation) {
        // Get or initialize counter for this player
        int count = entityCounter.getOrDefault(player.getUniqueId(), 0);
        EntityType entityType = EXAMPLE_ENTITIES[count % EXAMPLE_ENTITIES.length];
        entityCounter.put(player.getUniqueId(), count + 1);
        
        // Create spawn location above the target block
        Location spawnLocation = blockLocation.clone().add(0.5, 1.0, 0.5);
        
        try {
            Entity entity = player.getWorld().spawnEntity(spawnLocation, entityType);
            
            // Customize the entity
            customizeEntity(entity, player, count + 1);
            
            player.sendMessage(ColorSystem.colorize("&aSpawned " + entityType.name().toLowerCase() + " #" + (count + 1) + "!"));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            
        } catch (Exception ex) {
            player.sendMessage(ColorSystem.colorize("&cError spawning entity: " + ex.getMessage()));
        }
    }

    /**
     * Customizes the spawned entity with special properties
     * 
     * @param entity The entity to customize
     * @param player The player who spawned the entity
     * @param count The spawn count for naming
     */
    private void customizeEntity(@Nonnull Entity entity, @Nonnull Player player, int count) {
        // Set custom name
        entity.setCustomName(ColorSystem.colorize("&b" + player.getName() + "'s " + 
                           entity.getType().name().toLowerCase() + " #" + count));
        entity.setCustomNameVisible(true);
        
        // Add special effects based on entity type
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            
            // Add glowing effect
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0, false, false));
            
            // Customize based on entity type
            switch (entity.getType()) {
                case ZOMBIE:
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
                    if (livingEntity instanceof Zombie) {
                        ((Zombie) livingEntity).setBaby(count % 3 == 0);
                    }
                    break;
                    
                case SKELETON:
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, false, false));
                    break;
                    
                case CREEPER:
                    if (livingEntity instanceof Creeper) {
                        ((Creeper) livingEntity).setPowered(count % 5 == 0);
                    }
                    break;
                    
                case SHEEP:
                    if (livingEntity instanceof Sheep) {
                        Sheep sheep = (Sheep) livingEntity;
                        sheep.setColor(org.bukkit.DyeColor.values()[count % org.bukkit.DyeColor.values().length]);
                    }
                    break;
                    
                case COW:
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, Integer.MAX_VALUE, 2, false, false));
                    break;
                    
                case CHICKEN:
                    if (livingEntity instanceof Chicken) {
                        ((Chicken) livingEntity).setBaby();
                    }
                    break;
            }
        }
        
        // Make entity persistent
        entity.setPersistent(true);
    }

    /**
     * Removes all non-player entities within the specified radius
     * 
     * @param player The player removing the entities
     * @param centerLocation The center location for entity removal
     */
    private void removeNearbyEntities(@Nonnull Player player, @Nonnull Location centerLocation) {
        try {
            int removedCount = 0;
            
            for (Entity entity : centerLocation.getWorld().getNearbyEntities(centerLocation, REMOVAL_RADIUS, REMOVAL_RADIUS, REMOVAL_RADIUS)) {
                // Don't remove players
                if (entity instanceof Player) {
                    continue;
                }
                
                // Remove the entity
                entity.remove();
                removedCount++;
            }
            
            if (removedCount > 0) {
                player.sendMessage(ColorSystem.colorize("&aRemoved " + removedCount + " entit(ies)!"));
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            } else {
                player.sendMessage(ColorSystem.colorize("&7No entities found within " + REMOVAL_RADIUS + " blocks."));
            }
        } catch (Exception ex) {
            player.sendMessage(ColorSystem.colorize("&cError removing entities: " + ex.getMessage()));
        }
    }

    /**
     * Resets the entity counter for a specific player
     * This can be useful for cleanup or testing purposes
     * 
     * @param playerUUID The UUID of the player to reset
     */
    public static void resetPlayerCounter(@Nonnull UUID playerUUID) {
        entityCounter.remove(playerUUID);
    }

    /**
     * Gets the current entity count for a player
     * 
     * @param playerUUID The UUID of the player
     * @return The number of entities spawned by this player
     */
    public static int getPlayerEntityCount(@Nonnull UUID playerUUID) {
        return entityCounter.getOrDefault(playerUUID, 0);
    }
}