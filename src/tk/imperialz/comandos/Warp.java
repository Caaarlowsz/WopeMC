package tk.imperialz.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;
import tk.imperialz.apis.KitAPI;
import tk.imperialz.apis.WarpsMenu;
import tk.imperialz.warps.WarpsAPI;

public class Warp implements CommandExecutor {
	public WopePvP m;

	public Warp(final WopePvP m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
		final Player p = (Player) Sender;
		if (Cmd.getName().equalsIgnoreCase("warp")) {
			if (Args.length == 0) {
				WarpsMenu.inventory(p);
				return true;
			}
			if (Args[0].equalsIgnoreCase("fps")) {
				p.closeInventory();
				p.getInventory().setArmorContents((ItemStack[]) null);
				p.sendMessage("�3�lWARP �fVoc\u00ea est\u00e1 sendo teleportado.");
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setGameMode(GameMode.SURVIVAL);
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setArmorContents((ItemStack[]) null);
						KitAPI.DarSopa(p);
						KitAPI.setKit(p, "Fps");
						WarpsAPI.Ir(p, "Fps");
					}
				}, 1L);
			} else if (Args[0].equalsIgnoreCase("Evento")) {
				p.closeInventory();
				p.getInventory().setArmorContents((ItemStack[]) null);
				p.sendMessage("�3�lWARP �fVoc\u00ea est\u00e1 sendo teleportado.");
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setGameMode(GameMode.SURVIVAL);
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setArmorContents((ItemStack[]) null);
						KitAPI.setKit(p, "Evento");
						WarpsAPI.Ir(p, "Evento");
					}
				}, 1L);
			} else if (Args[0].equalsIgnoreCase("knockback")) {
				p.closeInventory();
				p.getInventory().setArmorContents((ItemStack[]) null);
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setGameMode(GameMode.SURVIVAL);
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setArmorContents((ItemStack[]) null);
						KitAPI.DarSopa(p);
						KitAPI.setKit(p, "Knockback");
						WarpsAPI.Ir(p, "Knockback");
						final ItemStack Espada = new ItemStack(Material.STICK);
						final ItemMeta kEspada = Espada.getItemMeta();
						kEspada.setDisplayName("�eGraveto");
						Espada.setItemMeta(kEspada);
						Espada.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
						p.getInventory().setItem(0, Espada);
					}
				}, 1L);
			} else if (Args[0].equalsIgnoreCase("1v1")) {
				p.sendMessage("�3�lWARP �fVoc\u00ea est\u00e1 sendo teleportado.");
				p.closeInventory();
				p.getInventory().setArmorContents((ItemStack[]) null);
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setGameMode(GameMode.SURVIVAL);
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setArmorContents((ItemStack[]) null);
						KitAPI.RemoveKit(p);
						KitAPI.setKit(p, "Spawn1v1");
						WarpsAPI.Ir(p, "1v1");
						final ItemStack Espada = new ItemStack(Material.BLAZE_ROD);
						final ItemMeta kEspada = Espada.getItemMeta();
						kEspada.setDisplayName("�e1v1");
						Espada.setItemMeta(kEspada);
						final ItemStack Espada2 = new ItemStack(Material.INK_SACK, 1, (short) 8);
						final ItemMeta kEspada2 = Espada2.getItemMeta();
						kEspada2.setDisplayName("�ePartida Rapida");
						Espada2.setItemMeta(kEspada2);
						p.getInventory().setItem(3, Espada);
						p.getInventory().setItem(5, Espada2);
					}
				}, 1L);
			} else if (Args[0].equalsIgnoreCase("challenge")) {
				p.sendMessage("�3�lWARP �fVoc\u00ea est\u00e1 sendo teleportado.");
				p.closeInventory();
				p.getInventory().setArmorContents((ItemStack[]) null);
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setGameMode(GameMode.SURVIVAL);
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setArmorContents((ItemStack[]) null);
						KitAPI.DarSopa(p);
						KitAPI.setKit(p, "Challenge");
						WarpsAPI.Ir(p, "Challenge");
					}
				}, 1L);
			}
		}
		return false;
	}
}
