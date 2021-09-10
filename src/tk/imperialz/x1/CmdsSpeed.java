package tk.imperialz.x1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import tk.imperialz.apis.KitAPI;
import tk.imperialz.eventos.Events1v1;
import tk.imperialz.eventos.Speed1v1;
import tk.imperialz.warps.WarpsAPI;

public class CmdsSpeed implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (Speed1v1.Partida1 && cmd.getName().equalsIgnoreCase("speed1v1")) {
			if (args.length == 0) {
				return true;
			}
			if (args.length == 1) {
				final Player Player2 = Bukkit.getPlayerExact(args[0]);
				if (Player2 instanceof Player) {
					Events1v1.Jogando1v1Player.add(p);
					Events1v1.Jogando1v1Player.add(Player2);
					Events1v1.JogandoSair.put(p.getName(), Player2.getName());
					Events1v1.JogandoSair.put(Player2.getName(), p.getName());
					Speed1v1.Iniciou.remove(Player2);
					Speed1v1.SECONDS.remove(Player2);
					WarpsAPI.Ir(Player2, "1v1loc2");
					WarpsAPI.Ir(p, "1v1loc1");
					Events1v1.tana1v1.add(p.getName());
					Events1v1.tana1v1.add(Player2.getName());
					Events1v1.ChamouDuelo.remove(Player2);
					Events1v1.AceitarDuelo.remove(Player2);
					Events1v1.ChamouDuelo.remove(p);
					Events1v1.AceitarDuelo.remove(p);
					p.getInventory().clear();
					Player2.getInventory().clear();
					Events1v1.inPvP.add(p);
					Events1v1.inPvP.add(Player2);
					final ItemStack Espada = new ItemStack(Material.DIAMOND_SWORD);
					Espada.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
					final ItemMeta Espada2 = Espada.getItemMeta();
					Espada2.setDisplayName("�eEspada");
					Espada.setItemMeta(Espada2);
					p.getInventory().addItem(new ItemStack[] { Espada });
					Player2.getInventory().addItem(new ItemStack[] { Espada });
					Player2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
					Player2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					Player2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					Player2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
					p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
					p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
					KitAPI.setKit(Player2, "1v1");
					KitAPI.setKit(p, "1v1");
					for (int i = 1; i < 9; ++i) {
						final ItemStack Fisherman = new ItemStack(Material.MUSHROOM_SOUP);
						p.getInventory().addItem(new ItemStack[] { Fisherman });
						Player2.getInventory().addItem(new ItemStack[] { Fisherman });
						Player2.updateInventory();
						p.updateInventory();
						if (Speed1v1.Iniciou.contains(p)) {
							Speed1v1.Iniciou.remove(p);
							Speed1v1.Partida1 = false;
						}
						if (Speed1v1.Iniciou.contains(Player2)) {
							Speed1v1.Iniciou.remove(Player2);
							Speed1v1.Partida1 = false;
						}
						Player[] onlinePlayers;
						for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
							final Player plr = onlinePlayers[j];
							Player2.hidePlayer(plr);
							p.hidePlayer(plr);
							p.showPlayer(Player2);
							Player2.showPlayer(p);
						}
					}
				}
			}
		}
		return false;
	}
}
