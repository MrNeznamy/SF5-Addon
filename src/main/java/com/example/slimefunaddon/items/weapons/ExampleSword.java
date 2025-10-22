package com.example.slimefunaddon.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
// Enchantable is now a property of SlimefunItem, not a separate interface
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedEnchantment;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

/**
 * A sharp sword forged from Example Ingots
 * Features:
 * - Extra damage to hostile mobs
 * - Built-in enchantments
 * - Special combat abilities
 */
public class ExampleSword extends SlimefunItem implements DamageableItem {

    public ExampleSword(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        addItemHandler(onEntityInteract());
        addItemHandler(onRightClick());
    }

    private EntityInteractHandler onEntityInteract() {
        return (event, item, offhand) -> {
            Player player = event.getPlayer();
            Entity entity = event.getRightClicked();
            
            if (entity instanceof LivingEntity target) {
                // Apply special effects when attacking
                applySpecialEffects(player, target, item);
            }
        };
    }

    private ItemUseHandler onRightClick() {
        return event -> {
                Player player = event.getPlayer();
                ItemStack item = event.getItem();
                
                if (item == null) return;
                
                // Special ability: Temporary strength boost
                if (player.isSneaking()) {
                    // Check cooldown (using item's custom model data as cooldown tracker)
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        long currentTime = System.currentTimeMillis();
                        long lastUse = meta.hasCustomModelData() ? meta.getCustomModelData() : 0;
                        
                        if (currentTime - lastUse > 30000) { // 30 second cooldown
                            // Apply strength effect
                            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 1)); // 10 seconds, level 2
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0)); // 10 seconds, level 1
                            
                            player.sendMessage("§6⚔ §eBojevní zuřivost aktivována!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.8f);
                            
                            // Update cooldown
                            meta.setCustomModelData((int) currentTime);
                            item.setItemMeta(meta);
                            
                            // Damage the sword for using special ability
                            if (isDamageable()) {
                                damageItem(player, item, 5);
                            }
                        } else {
                            int remainingCooldown = (int) ((30000 - (currentTime - lastUse)) / 1000);
                            player.sendMessage("§cSchopnost je na cooldownu! Zbývá: " + remainingCooldown + "s");
                        }
                    }
                }
        };
    }

    private void applySpecialEffects(Player player, LivingEntity target, ItemStack item) {
        // Extra damage to monsters
        if (target instanceof Monster) {
            // Apply fire effect
            target.setFireTicks(60); // 3 seconds of fire
            
            // Chance for weakness effect
            if (Math.random() < 0.25) { // 25% chance
                target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
                player.sendMessage("§6⚔ §eNepřítel je oslaben!");
            }
            
            // Chance for critical hit
            if (Math.random() < 0.20) { // 20% chance
                target.damage(4.0, player); // Extra damage
                player.sendMessage("§6⚔ §eKritický zásah!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.2f);
            }
        }
        
        // Damage the sword
        if (isDamageable()) {
            damageItem(player, item, 1);
        }
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    public void damageItem(Player player, ItemStack item, int damage) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            // Use durability system
            int currentDamage = item.getType().getMaxDurability() - item.getDurability();
            int newDamage = currentDamage + damage;
            
            // Check if weapon should break
            if (newDamage >= getMaxDurability()) {
                item.setAmount(0);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                player.sendMessage("§cVáš Example Sword se rozbil!");
            } else {
                item.setDurability((short) (item.getType().getMaxDurability() - newDamage));
            }
        }
    }

    /**
     * Get the maximum durability of this weapon
     */
    private int getMaxDurability() {
        return 2000; // Higher than diamond sword
    }

    // Enchantability is now a property of SlimefunItem, not separate methods
    // Enchantments are added in the static block in ExampleItemStacks

    // Enchantments are now added in the static block in ExampleItemStacks
}