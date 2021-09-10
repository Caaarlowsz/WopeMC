package tk.imperialz.apis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import tk.imperialz.Main;

public class Placa implements Listener {
	public Main m;

	public Placa(final Main m) {
		this.m = m;
	}

	@EventHandler
	public void Sign(final SignChangeEvent event) {
		final Player player = event.getPlayer();
		if (!this.m.perm.isAdmin(player)) {
			return;
		}
		for (int i = 0; i <= 3; ++i) {
			String line = event.getLine(i);
			line = ChatColor.translateAlternateColorCodes('&', line);
			event.setLine(i, line);
		}
	}

	@EventHandler
	public void SignClickEvent(final PlayerInteractEvent e) {
		final ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
		final ItemMeta sopas = sopa.getItemMeta();
		sopas.setDisplayName("§aSopa");
		sopa.setItemMeta(sopas);
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN
				|| e.getClickedBlock().getType() == Material.SIGN_POST
				|| e.getClickedBlock().getType() == Material.WALL_SIGN)) {
			final Sign sign = (Sign) e.getClickedBlock().getState();
			if (sign.getLine(0).equalsIgnoreCase("§a-§e-§b-§c-§d-") && sign.getLine(1).equalsIgnoreCase("§b§lPLACA")
					&& sign.getLine(2).equalsIgnoreCase("Sopas")
					&& sign.getLine(3).equalsIgnoreCase("§a-§e-§b-§c-§d-")) {
				final Inventory v = Bukkit.createInventory((InventoryHolder) null, 36, "§aSopas");
				for (int i = 0; i < 36; ++i) {
					v.addItem(new ItemStack[] { new ItemStack(sopa) });
				}
				e.getPlayer().openInventory(v);
			}
		}
	}

	@EventHandler
	public void onSignChangeRecraft(final SignChangeEvent event) {
		if (event.getLine(0).equals("recraft")) {
			event.setLine(0, "§a-§e-§b-§c-§d-");
			event.setLine(1, "§b§lPLACA");
			event.setLine(2, "Recraft");
			event.setLine(3, "§a-§e-§b-§c-§d-");
		}
	}

	@EventHandler
	public void onSignChangeSoup(final SignChangeEvent event) {
		if (event.getLine(0).equals("sopa")) {
			event.setLine(0, "§a-§e-§b-§c-§d-");
			event.setLine(1, "§b§lPLACA");
			event.setLine(2, "Sopas");
			event.setLine(3, "§a-§e-§b-§c-§d-");
		}
	}

	@EventHandler
	public void SignClickEvent1(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN
				|| e.getClickedBlock().getType() == Material.SIGN_POST
				|| e.getClickedBlock().getType() == Material.WALL_SIGN)) {
			final Sign sign = (Sign) e.getClickedBlock().getState();
			if (sign.getLine(0).equalsIgnoreCase("§a-§e-§b-§c-§d-") && sign.getLine(1).equalsIgnoreCase("§b§lPLACA")
					&& sign.getLine(2).equalsIgnoreCase("Recraft")
					&& sign.getLine(3).equalsIgnoreCase("§a-§e-§b-§c-§d-")) {
				final ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
				final ItemMeta n = cogu.getItemMeta();
				n.setDisplayName("§cCogumelo");
				cogu.setItemMeta(n);
				final ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
				final ItemMeta m = cogu2.getItemMeta();
				m.setDisplayName("§eCogumelo");
				cogu2.setItemMeta(m);
				final ItemStack pote = new ItemStack(Material.BOWL, 64);
				final ItemMeta o = pote.getItemMeta();
				o.setDisplayName("§7Pote");
				pote.setItemMeta(o);
				final Inventory v = Bukkit.createInventory((InventoryHolder) null, 9, "§aRecraft");
				for (int i = 0; i < 3; ++i) {
					v.addItem(new ItemStack[] { new ItemStack(pote) });
				}
				for (int i = 0; i < 3; ++i) {
					v.addItem(new ItemStack[] { new ItemStack(cogu) });
				}
				for (int i = 0; i < 3; ++i) {
					v.addItem(new ItemStack[] { new ItemStack(cogu2) });
				}
				e.getPlayer().openInventory(v);
			}
		}
	}
}
