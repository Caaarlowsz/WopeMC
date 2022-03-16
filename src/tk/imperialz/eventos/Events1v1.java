package tk.imperialz.eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;
import tk.imperialz.apis.KitAPI;
import tk.imperialz.warps.WarpsAPI;
import tk.imperialz.x1.Join1v1;

public class Events1v1 implements Listener {
	public static ArrayList<String> tana1v1;
	public static List<Player> ChamouDuelo;
	public static List<Player> AceitarDuelo;
	public static List<Player> Jogando1v1Player;
	public static List<Player> inPvP;
	public static HashMap<String, String> JogandoSair;

	static {
		Events1v1.tana1v1 = new ArrayList<String>();
		Events1v1.ChamouDuelo = new ArrayList<Player>();
		Events1v1.AceitarDuelo = new ArrayList<Player>();
		Events1v1.Jogando1v1Player = new ArrayList<Player>();
		Events1v1.inPvP = new ArrayList<Player>();
		Events1v1.JogandoSair = new HashMap<String, String>();
	}

	@EventHandler
	public void PlayerInteractEntityEvent4(final PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		if (p.getItemInHand().equals((Object) Join1v1.Item_)) {
			if (Events1v1.ChamouDuelo.contains(p)) {
				p.sendMessage("�cAguarde para convidar outra pessoa.");
				return;
			}
			final Player Player2 = (Player) event.getRightClicked();
			Events1v1.AceitarDuelo.add(Player2);
			Events1v1.ChamouDuelo.add(p);
			p.sendMessage("�6�l1V1 �fVoc\u00ea convidou o jogador �b" + Player2.getName() + " �fpara uma batalha 1v1.");
			Player2.sendMessage(
					"�6�l1V1 �fVoc\u00ea foi convidado pelo jogador �b" + p.getName() + " �fpara uma batalha 1v1");
			Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (Events1v1.ChamouDuelo.contains(p)) {
						Events1v1.ChamouDuelo.remove(p);
						Events1v1.AceitarDuelo.remove(Player2);
					}
				}
			}, 100L);
		}
	}

	@EventHandler
	public void PlayerInteractEntityEvent3(final PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		if (Events1v1.AceitarDuelo.contains(p) && p.getItemInHand().equals((Object) Join1v1.Item_)) {
			final Player Player2 = (Player) event.getRightClicked();
			if (Events1v1.ChamouDuelo.contains(Player2)) {
				Events1v1.Jogando1v1Player.add(p);
				Events1v1.Jogando1v1Player.add(Player2);
				Events1v1.JogandoSair.put(p.getName(), Player2.getName());
				Events1v1.JogandoSair.put(Player2.getName(), p.getName());
				KitAPI.setKit(Player2, "1v1");
				KitAPI.setKit(p, "1v1");
				WarpsAPI.Ir(Player2, "1v1loc2");
				WarpsAPI.Ir(p, "1v1loc1");
				Events1v1.ChamouDuelo.remove(Player2);
				Events1v1.AceitarDuelo.remove(Player2);
				Events1v1.ChamouDuelo.remove(p);
				Events1v1.AceitarDuelo.remove(p);
				Events1v1.inPvP.add(p);
				Events1v1.inPvP.add(Player2);
				p.getInventory().clear();
				Player2.getInventory().clear();
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
					Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
						@Override
						public void run() {
							Player[] onlinePlayers;
							for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length,
									i = 0; i < length; ++i) {
								final Player plr = onlinePlayers[i];
								Player2.hidePlayer(plr);
								p.hidePlayer(plr);
								p.showPlayer(Player2);
								Player2.showPlayer(p);
							}
						}
					}, 2L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							p.setMaxHealth(20);
							p.setHealth(20);
							Player2.setMaxHealth(20);
							Player2.setHealth(20);
						}
					}, 20L);
				}
			}
		}
	}

	@EventHandler
	public void Morrer2(final PlayerDeathEvent e) {
		final Player v = e.getEntity();
		e.setDeathMessage((String) null);
		if (e.getEntity().getKiller() instanceof Player) {
			final Player m = e.getEntity().getKiller();
			if (m != v) {
				if (!Events1v1.Jogando1v1Player.contains(m) && !Events1v1.Jogando1v1Player.contains(v)) {
					Events1v1.Jogando1v1Player.remove(v);
					Events1v1.Jogando1v1Player.remove(m);
					v.setLevel(0);
					return;
				}
				final int amountv = this.itemsInInventory((Inventory) v.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				final int amountm = this.itemsInInventory((Inventory) m.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				Events1v1.inPvP.remove(m);
				Events1v1.inPvP.remove(v);
				m.sendMessage("     ");
				m.sendMessage("�6�l1V1 �fVoc\u00ea ganhou de jogador �b" + v.getName() + " �fe ele ficou com �c"
						+ amountv + " �fsopas.");
				KitAPI.RemoveKit(m);
				KitAPI.RemoveKit(v);
				v.sendMessage("     ");
				v.sendMessage("�6�l1V1 �fVoc\u00ea perdeu para o jogador �b" + m.getName() + " �fe ele ficou com �c"
						+ amountm + " �fsopas.");
				WarpsAPI.Ir(m, "1v1");
				m.setGameMode(GameMode.SURVIVAL);
				m.getInventory().setArmorContents((ItemStack[]) null);
				m.getInventory().clear();
				for (final PotionEffect effect : m.getActivePotionEffects()) {
					m.removePotionEffect(effect.getType());
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						WarpsAPI.Ir(v, "1v1");
						v.setGameMode(GameMode.SURVIVAL);
						v.getInventory().setArmorContents((ItemStack[]) null);
						v.getInventory().clear();
						for (final PotionEffect effect : v.getActivePotionEffects()) {
							v.removePotionEffect(effect.getType());
						}
					}
				}, 1L);
				KitAPI.RemoveKit(m);
				KitAPI.RemoveKit(v);
				Events1v1.Jogando1v1Player.remove(v);
				Events1v1.Jogando1v1Player.remove(m);
				Events1v1.JogandoSair.remove(m.getName());
				Events1v1.JogandoSair.remove(v.getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						v.getInventory().setItem(3, Join1v1.Item_);
						v.getInventory().setItem(5, Join1v1.Cinza);
						m.getInventory().setItem(3, Join1v1.Item_);
						m.getInventory().setItem(5, Join1v1.Cinza);
						m.updateInventory();
						v.updateInventory();
						WarpsAPI.Ir(v, "1v1");
						KitAPI.RemoveKit(m);
						KitAPI.RemoveKit(v);
					}
				}, 2L);
				m.setHealth(20);
				v.setHealth(20);
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
					final Player plr = onlinePlayers[i];
					v.showPlayer(plr);
					m.showPlayer(plr);
				}
			}
		}
	}

	@EventHandler
	public void Morrer3(final PlayerDeathEvent e) {
		final Player v = e.getEntity();
		if (e.getEntity().getKiller() instanceof Player) {
			final Player m = e.getEntity().getKiller();
			if (m != v) {
				if (!Events1v1.Jogando1v1Player.contains(v) && !Events1v1.Jogando1v1Player.contains(m)) {
					Events1v1.Jogando1v1Player.remove(v);
					Events1v1.Jogando1v1Player.remove(m);
					v.setLevel(0);
					return;
				}
				final int amountv = this.itemsInInventory((Inventory) v.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				final int amountm = this.itemsInInventory((Inventory) m.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				m.sendMessage("     ");
				m.sendMessage("�6�l1V1 �fVoc\u00ea ganhou de jogador �b" + v.getName() + " �fe ele ficou com �c"
						+ amountv + " �fsopas.");
				Events1v1.inPvP.remove(m);
				Events1v1.inPvP.remove(v);
				KitAPI.setKit(v, "Nenhum");
				KitAPI.setKit(m, "Nenhum");
				v.sendMessage("     ");
				v.sendMessage("�6�l1V1 �fVoc\u00ea perdeu para o jogador �b" + m.getName() + " �fe ele ficou com �c"
						+ amountm + " �fsopas.");
				WarpsAPI.Ir(m, "1v1");
				m.setGameMode(GameMode.SURVIVAL);
				m.getInventory().setArmorContents((ItemStack[]) null);
				m.getInventory().clear();
				v.setLevel(0);
				for (final PotionEffect effect : m.getActivePotionEffects()) {
					m.removePotionEffect(effect.getType());
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						WarpsAPI.Ir(v, "1v1");
						v.setGameMode(GameMode.SURVIVAL);
						v.getInventory().setArmorContents((ItemStack[]) null);
						v.getInventory().clear();
						for (final PotionEffect effect : v.getActivePotionEffects()) {
							v.removePotionEffect(effect.getType());
						}
					}
				}, 1L);
				Events1v1.Jogando1v1Player.remove(v);
				Events1v1.Jogando1v1Player.remove(m);
				Events1v1.JogandoSair.remove(m.getName());
				Events1v1.JogandoSair.remove(v.getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						v.getInventory().setItem(3, Join1v1.Item_);
						v.getInventory().setItem(5, Join1v1.Cinza);
						m.getInventory().setItem(3, Join1v1.Item_);
						m.getInventory().setItem(5, Join1v1.Cinza);
						m.updateInventory();
						v.updateInventory();
						WarpsAPI.Ir(v, "1v1");
						KitAPI.setKit(m, "Nenhum");
						KitAPI.setKit(v, "Nenhum");
					}
				}, 2L);
				m.setHealth(20);
				v.setHealth(20);
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
					final Player plr = onlinePlayers[i];
					v.showPlayer(plr);
					m.showPlayer(plr);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeft(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		if (Events1v1.JogandoSair.containsKey(p.getName())) {
			final Player t = Bukkit.getServer().getPlayer((String) Events1v1.JogandoSair.get(p.getName()));
			WarpsAPI.Ir(t, "1v1");
			Events1v1.JogandoSair.remove(t.getName());
			Events1v1.JogandoSair.remove(p.getName());
			Events1v1.Jogando1v1Player.remove(p);
			Events1v1.Jogando1v1Player.remove(t);
			Events1v1.inPvP.remove(p);
			Events1v1.inPvP.remove(t);
			t.setMaxHealth(20.0);
			t.setHealth(20.0);
			KitAPI.setKit(t, "Nenhum");
			t.setGameMode(GameMode.SURVIVAL);
			t.getInventory().setArmorContents((ItemStack[]) null);
			t.getInventory().clear();
			for (final PotionEffect effect : t.getActivePotionEffects()) {
				t.removePotionEffect(effect.getType());
			}
			t.sendMessage("�6�l1V1: �fO jogador �b" + p.getName() + " �fdeslogou no 1v1.");
			t.getInventory().setItem(3, Join1v1.Item_);
			t.getInventory().setItem(5, Join1v1.Cinza);
			t.updateInventory();
			Player[] onlinePlayers;
			for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
				final Player plr = onlinePlayers[i];
				t.showPlayer(plr);
				p.showPlayer(plr);
			}
		}
	}

	@EventHandler
	public void cmda(final PlayerCommandPreprocessEvent event) {
		final Player p = event.getPlayer();
		if (Events1v1.Jogando1v1Player.contains(p)) {
			event.getPlayer().sendMessage("�cSem comandos em batalha.");
			event.setCancelled(true);
		}
	}

	public int itemsInInventory(final Inventory inventory, final Material[] search) {
		final List<Material> wanted = Arrays.asList(search);
		int found = 0;
		ItemStack[] arrayOfItemStack;
		for (int j = (arrayOfItemStack = inventory.getContents()).length, i = 0; i < j; ++i) {
			final ItemStack item = arrayOfItemStack[i];
			if (item != null && wanted.contains(item.getType())) {
				found += item.getAmount();
			}
		}
		return found;
	}

	@EventHandler
	public void PlayerInteractEntityEvent2(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player pl = onlinePlayers[i];
			if (Events1v1.Jogando1v1Player.contains(pl)) {
				pl.hidePlayer(p);
			}
		}
	}
}
