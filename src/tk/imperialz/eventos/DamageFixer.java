package tk.imperialz.eventos;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageFixer implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageEvent(final EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) event.getDamager();
		final ItemStack sword = p.getItemInHand();
		double damage = event.getDamage();
		final double danoEspada = this.getDamage(sword.getType());
		boolean isMore = false;
		if (damage > 2.0) {
			isMore = true;
		}
		if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
			for (final PotionEffect effect : p.getActivePotionEffects()) {
				if (effect.getType().equals((Object) PotionEffectType.INCREASE_DAMAGE)) {
					double minus = 0.0;
					if (this.isCrital(p)) {
						event.setDamage(0.0);
						return;
					}
					minus = danoEspada * 1.3 * (effect.getAmplifier() + 1);
					damage -= minus;
					damage += 2 * (effect.getAmplifier() + 2);
					break;
				}
			}
		}
		if (!sword.getEnchantments().isEmpty()) {
			if (sword.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS) && this.isArthropod(event.getEntityType())) {
				damage -= 1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_UNDEAD) && this.isUndead(event.getEntityType())) {
				damage -= 1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
				damage -= 1.25 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			}
		}
		if (this.isCrital(p)) {
			damage -= danoEspada / 2.0 + 1.0;
			damage += 3.0;
		}
		if (isMore) {
			damage -= 2.0;
		}
		event.setDamage(damage);
	}

	private boolean isCrital(final Player p) {
		return p.getFallDistance() > 0.0f && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
	}

	private boolean isArthropod(final EntityType type) {
		switch (type) {
		case MINECART_HOPPER: {
			return true;
		}
		case LEASH_HITCH: {
			return true;
		}
		case MINECART_MOB_SPAWNER: {
			return true;
		}
		default: {
			return false;
		}
		}
	}

	private boolean isUndead(final EntityType type) {
		switch (type) {
		case ITEM_FRAME: {
			return true;
		}
		case MAGMA_CUBE: {
			return true;
		}
		case ENDER_CRYSTAL: {
			return true;
		}
		case MINECART_COMMAND: {
			return true;
		}
		default: {
			return false;
		}
		}
	}

	private double getDamage(final Material type) {
		double damage = 1.0;
		if (type.toString().contains("DIAMOND_")) {
			damage = 9.0;
		} else if (type.toString().contains("IRON_")) {
			damage = 8.0;
		} else if (type.toString().contains("STONE_")) {
			damage = 7.0;
		} else if (type.toString().contains("WOOD_")) {
			damage = 6.0;
		} else if (type.toString().contains("GOLD_")) {
			damage = 6.0;
		}
		if (!type.toString().contains("_SWORD")) {
			--damage;
			if (!type.toString().contains("_AXE")) {
				--damage;
				if (!type.toString().contains("_PICKAXE")) {
					--damage;
					if (!type.toString().contains("_SPADE")) {
						damage = 1.0;
					}
				}
			}
		}
		return damage;
	}
}
