package tk.imperialz.apis;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class APITag {
	public static ItemStack criarItem(final Player p, final Material material, final String nome, final String[] lore,
			final int quantidade, final short cor) {
		final ItemStack item = new ItemStack(material, quantidade, cor);
		final ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(lore));
		item.setItemMeta(kitem);
		return item;
	}

	public static String Cargo(final Player p) {
		if (p.hasPermission("heavenmc.dono")) {
			return "DONO";
		}
		if (p.hasPermission("heavenmc.diretor")) {
			return "DIRETOR";
		}
		if (p.hasPermission("heavenmc.admin")) {
			return "ADMIN";
		}
		if (p.hasPermission("heavenmc.gerente")) {
			return "GERENTE";
		}
		if (p.hasPermission("heavenmc.modplus")) {
			return "MOD+";
		}
		if (p.hasPermission("heavenmc.mod")) {
			return "MOD";
		}
		if (p.hasPermission("heavenmc.youtuberplus")) {
			return "YOUTUBER+";
		}
		if (p.hasPermission("heavenmc.trial")) {
			return "TRIALMOD";
		}
		if (p.hasPermission("heavenmc.builder")) {
			return "BUILDER";
		}
		if (p.hasPermission("heavenmc.youtuber")) {
			return "YOUTUBER";
		}
		if (p.hasPermission("heavenmc.legend")) {
			return "LEGEND";
		}
		if (p.hasPermission("heavenmc.extreme")) {
			return "EXTREME";
		}
		if (p.hasPermission("heavenmc.pro")) {
			return "PRO";
		}
		if (p.hasPermission("heavenmc.low")) {
			return "LOW";
		}
		return ChatColor.GRAY + "Membro";
	}
}
