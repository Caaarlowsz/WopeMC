package tk.imperialz.apis;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WarpsMenu {
	public static ItemStack criarItemMenu(final Material material, final String nome, final String[] habilidade) {
		final ItemStack item = new ItemStack(material);
		final ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		return item;
	}

	public static void inventory(final Player p) {
		final Inventory menu = Bukkit.createInventory((InventoryHolder) p, 9, "§7Menu de Warps");
		menu.addItem(new ItemStack[] { criarItemMenu(Material.GLASS, "§dFPS", new String[] { "" }) });
		menu.addItem(new ItemStack[] { criarItemMenu(Material.BLAZE_ROD, "§d1V1", new String[] { "" }) });
		menu.addItem(new ItemStack[] { criarItemMenu(Material.LAVA, "§dLava Challenge", new String[] { "" }) });
		menu.addItem(new ItemStack[] { criarItemMenu(Material.STICK, "§dKnockack", new String[] { "" }) });
		menu.addItem(new ItemStack[] { criarItemMenu(Material.EMERALD, "§dEvento", new String[] { "" }) });
		p.openInventory(menu);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
	}
}
