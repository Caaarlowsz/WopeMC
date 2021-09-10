package tk.imperialz.eventos;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import tk.imperialz.apis.APITag;
import tk.imperialz.apis.KitAPI;

public class Kit implements Listener {
	public static ItemStack criarItemMenu(final Material material, final String nome, final String[] habilidade) {
		final ItemStack item = new ItemStack(material);
		final ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		return item;
	}

	public static void inventariokits(final Player p) {
		final Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, "§7Seletor de Kits");
		final ItemStack tvidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
		final ItemMeta tvidroetaa = tvidro.getItemMeta();
		tvidroetaa.setDisplayName("§6§lWope");
		tvidro.setItemMeta(tvidroetaa);
		final ItemStack vine = new ItemStack(Material.VINE);
		final ItemMeta vinemeta = tvidro.getItemMeta();
		vinemeta.setDisplayName("§6§lWope");
		vine.setItemMeta(vinemeta);
		inv.setItem(0, tvidro);
		inv.setItem(1, tvidro);
		inv.setItem(2, tvidro);
		inv.setItem(3, tvidro);
		inv.setItem(4, tvidro);
		inv.setItem(5, tvidro);
		inv.setItem(6, tvidro);
		inv.setItem(7, tvidro);
		inv.setItem(8, tvidro);
		if (p.hasPermission("kit.pvp")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.STONE_SWORD, "§aPvP", new String[] { "" }) });
		}
		if (p.hasPermission("kit.kangaroo")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.FIREWORK, "§aKangaroo", new String[] { "" }) });
		}
		if (p.hasPermission("kit.ninja")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.EMERALD, "§aNinja", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.SOUL_SAND, "§aSnail", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.FISHING_ROD, "§aFisherman", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.LEATHER_BOOTS, "§aStomper", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.NETHER_STAR, "§aAjnin", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.ANVIL, "§aAnchor", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.BLAZE_ROD, "§aMonk", new String[] { "" }) });
		}
		if (p.hasPermission("kit.gladiator")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.IRON_FENCE, "§aGladiator", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.SPIDER_EYE, "§aViper", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.LAVA_BUCKET, "§aMagma", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.WATER_BUCKET, "§aPoseidon", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.FEATHER, "§aPhantom", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.BOOK, "§aSpecialist", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.SPONGE, "§aLauncher", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.WOOD_HOE, "§aReaper", new String[] { "" }) });
		}
		if (p.hasPermission("heavenmc.low")) {
			inv.addItem(new ItemStack[] { criarItemMenu(Material.DIAMOND_AXE, "§aViking", new String[] { "" }) });
		}
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1.0f, 1.0f);
	}

	@EventHandler
	public void inventariowarps(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§7Menu de Warps") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp 1v1");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.GLASS) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp fps");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.EMERALD) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp evento");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BOOKSHELF) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp textura");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAVA) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp challenge");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.STICK) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/warp knockback");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
			}
		}
	}

	@EventHandler
	public void inventariokitsevt2(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§7Seletor de Kits") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.setKit(p, "PvP");
				KitAPI.DarSopa(p);
				final ItemStack espadapvp = new ItemStack(Material.STONE_SWORD);
				final ItemMeta espadapvpmeta = espadapvp.getItemMeta();
				espadapvpmeta.setDisplayName("§eEspada");
				espadapvp.setItemMeta(espadapvpmeta);
				espadapvp.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				p.getInventory().setItem(0, espadapvp);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Viper");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Magma");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Anchor");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Poseidon");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SPONGE) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Reaper");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.WOOD_HOE, ChatColor.GREEN + "Reaper",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FEATHER) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Phantom");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.FEATHER, ChatColor.GREEN + "Phantom",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Gladiator");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.IRON_FENCE, ChatColor.GREEN + "Gladiator",
						new String[1], 1, (short) 0));
				p.getInventory().setItem(2, APITag.criarItem(p, Material.COBBLESTONE, ChatColor.GREEN + "Blocos",
						new String[1], 16, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LEASH) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Grappler");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				p.getInventory().setItem(1,
						APITag.criarItem(p, Material.LEASH, ChatColor.GREEN + "Grappler", new String[1], 1, (short) 0));
				return;
			}
			if (e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Viking");
				p.getInventory().setItem(0, APITag.criarItem(p, Material.STONE_AXE, ChatColor.GREEN + "Viking",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.GRAVEL) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Demoman");
				p.getInventory().setItem(1,
						APITag.criarItem(p, Material.TNT, ChatColor.GREEN + "Demoman", new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.WOOD_AXE) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Thor");
				p.getInventory().setItem(1,
						APITag.criarItem(p, Material.WOOD_AXE, ChatColor.GREEN + "Thor", new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.EMERALD) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.setKit(p, "Ninja");
				KitAPI.DarSopa(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.setKit(p, "Monk");
				KitAPI.DarSopa(p);
				p.getInventory().setItem(1,
						APITag.criarItem(p, Material.BLAZE_ROD, ChatColor.GREEN + "Monk", new String[1], 1, (short) 0));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FIREWORK) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Kangaroo");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.FIREWORK, ChatColor.GREEN + "Kangaroo",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SOUL_SAND) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Snail");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BOOK) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Specialist");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.BOOK, ChatColor.GREEN + "Specialist",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Stomper");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Fisherman");
				p.getInventory().setItem(1, APITag.criarItem(p, Material.FISHING_ROD, ChatColor.GREEN + "Fisherman",
						new String[1], 1, (short) 0));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
				return;
			}
			if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
				e.setCancelled(true);
				p.closeInventory();
				KitAPI.DarSopa(p);
				KitAPI.setKit(p, "Ajnin");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.0f, 2.0f);
			}
		}
	}
}
