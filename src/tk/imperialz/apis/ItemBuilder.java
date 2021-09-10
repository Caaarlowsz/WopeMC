package tk.imperialz.apis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
	public static ItemStack criarItem(final Material mat) {
		final ItemStack item = new ItemStack(mat);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome) {
		final ItemStack item = new ItemStack(mat);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final int tipo) {
		final ItemStack item = new ItemStack(mat, 1, (short) tipo);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final int quantidade, final String desc) {
		final ItemStack item = new ItemStack(mat, quantidade);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		final List<String> desc2 = new ArrayList<String>();
		desc2.add(desc);
		itemm.setLore(desc2);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final int tipo, final String desc) {
		final ItemStack item = new ItemStack(mat, 1, (short) tipo);
		final ItemMeta itemm = item.getItemMeta();
		final List<String> desc2 = new ArrayList<String>();
		desc2.add(desc);
		itemm.setLore(desc2);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final String desc) {
		final ItemStack item = new ItemStack(mat, 1);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		final List<String> desc2 = new ArrayList<String>();
		desc2.add(desc);
		itemm.setLore(desc2);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final List<String> desc) {
		final ItemStack item = new ItemStack(mat, 1);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		itemm.setLore(desc);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final int quantidade) {
		final ItemStack item = new ItemStack(mat, quantidade);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final int quantidade, final int tipo) {
		final ItemStack item = new ItemStack(mat, quantidade, (short) tipo);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItemInv(final Material mat, final String nome, final int quantidade, final int tipo,
			final int slot, final Inventory inv) {
		final ItemStack item = new ItemStack(mat, quantidade, (short) tipo);
		final ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		inv.setItem(slot, item);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final Enchantment enchant, final int level) {
		final ItemStack item = new ItemStack(mat);
		final ItemMeta itemm = item.getItemMeta();
		itemm.addEnchant(enchant, level, true);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final Enchantment enchant,
			final int level) {
		final ItemStack item = new ItemStack(mat);
		final ItemMeta itemm = item.getItemMeta();
		itemm.addEnchant(enchant, level, true);
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final int tipo, final Enchantment enchant, final int level) {
		final ItemStack item = new ItemStack(mat, 1, (short) tipo);
		final ItemMeta itemm = item.getItemMeta();
		itemm.addEnchant(enchant, level, true);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final int quantidade,
			final Enchantment enchant, final int level) {
		final ItemStack item = new ItemStack(mat, quantidade);
		final ItemMeta itemm = item.getItemMeta();
		itemm.addEnchant(enchant, level, true);
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItem(final Material mat, final String nome, final int quantidade, final int tipo,
			final Enchantment enchant, final int level) {
		final ItemStack item = new ItemStack(mat, quantidade, (short) tipo);
		final ItemMeta itemm = item.getItemMeta();
		itemm.addEnchant(enchant, level, true);
		itemm.setDisplayName(nome);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack criarItemHead(final String nome, final String nomeplayer) {
		final ItemStack item = new ItemStack(Material.getMaterial(397));
		final SkullMeta sm = (SkullMeta) item.getItemMeta();
		item.setDurability((short) 3);
		sm.hasOwner();
		sm.setOwner(nomeplayer);
		sm.setDisplayName(nome);
		item.setItemMeta((ItemMeta) sm);
		return item;
	}

	public static ItemStack criarItemHead(final String nome, final String nomeplayer, final List<String> lore) {
		final ItemStack item = new ItemStack(Material.getMaterial(397));
		final SkullMeta sm = (SkullMeta) item.getItemMeta();
		item.setDurability((short) 3);
		sm.setLore(lore);
		sm.hasOwner();
		sm.setOwner(nomeplayer);
		sm.setDisplayName(nome);
		item.setItemMeta((ItemMeta) sm);
		return item;
	}

	public static ItemStack criarArmadura(final Material mat, final Color cor) {
		final ItemStack item = new ItemStack(mat);
		final LeatherArmorMeta itemm = (LeatherArmorMeta) item.getItemMeta();
		itemm.setColor(cor);
		item.setItemMeta((ItemMeta) itemm);
		return item;
	}

	public static int VerificarItemNoInv(final Player p, final Material mat) {
		int valor = 0;
		ItemStack[] contents;
		for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
			final ItemStack item = contents[i];
			if (item != null && item.getType() == mat && item.getAmount() > 0) {
				valor += item.getAmount();
			}
		}
		return valor;
	}
}
