package tk.imperialz.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import tk.imperialz.Main;
import tk.imperialz.apis.KitAPI;
import tk.imperialz.apis.WarpsMenu;
import tk.imperialz.gamer.DataManager;
import tk.imperialz.gamer.PlayerData;
import tk.imperialz.mysql.MySQLFunctions;
import tk.imperialz.scoreboard.Scoreboarde;
import tk.imperialz.warps.WarpsAPI;

public class Listeners implements Listener {

	static {
	}

	@EventHandler
	public void respawn(final PlayerDeathEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		new BukkitRunnable() {
			public void run() {
				e.getEntity().spigot().respawn();
			}
		}.runTask(Main.getPlugin());
	}

	public void flecha(final ProjectileHitEvent e) {
		final Entity entity = (Entity) e.getEntity();
		if (entity.getType() == EntityType.ARROW) {
			entity.remove();
		}
	}

	@EventHandler
	public void preLogin(final AsyncPlayerPreLoginEvent e) {
		final PlayerData playerData = new PlayerData(e.getUniqueId(), e.getName());
		if (!MySQLFunctions.VerificarRegistro(e.getUniqueId())) {
			playerData.create();
		}
		playerData.load();
		DataManager.addPlayerData(playerData);
	}

	@EventHandler
	public void deadChallenge(final PlayerDeathEvent e) {
		final Player v = e.getEntity();
		e.setDeathMessage((String) null);
		if (!(e.getEntity().getKiller() instanceof Player) && KitAPI.getkit(v) == "Challenge") {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					WarpsAPI.Ir(v, "Challenge");
					v.getInventory().setArmorContents((ItemStack[]) null);
					v.getInventory().clear();
					for (final PotionEffect effect : v.getActivePotionEffects()) {
						v.removePotionEffect(effect.getType());
					}
				}
			}, 1L);
			KitAPI.RemoveKit(v);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					KitAPI.DarSopa(v);
					v.updateInventory();
					WarpsAPI.Ir(v, "Challenge");
					KitAPI.setKit(v, "Challenge");
					v.sendMessage("§c§lDEATH §fVoc\u00ea §c§lMORREU§f na warp challenge.");
					v.sendMessage("§c§lDEATH §fSeus §a§lSTATUS§f n\u00e3o ser\u00e3o afetados!");
				}
			}, 2L);
			((Damageable) v).setHealth(20.0);
		}
	}

	@EventHandler
	public void deadEvento(final PlayerDeathEvent e) {
		final Player v = e.getEntity();
		e.setDeathMessage((String) null);
		if (e.getEntity().getKiller() instanceof Player && KitAPI.getkit(v) == "Evento") {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					WarpsAPI.Ir(v, "Evento");
					v.getInventory().setArmorContents((ItemStack[]) null);
					v.getInventory().clear();
					for (final PotionEffect effect : v.getActivePotionEffects()) {
						v.removePotionEffect(effect.getType());
					}
				}
			}, 1L);
			KitAPI.RemoveKit(v);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					KitAPI.DarSopa(v);
					v.updateInventory();
					WarpsAPI.Ir(v, "Evento");
					KitAPI.setKit(v, "Evento");
				}
			}, 2L);
			((Damageable) v).setHealth(20.0);
		}
	}

	@EventHandler
	public void deadFps(final PlayerDeathEvent e) {
		final Player v = e.getEntity();
		e.setDeathMessage((String) null);
		if (e.getEntity().getKiller() instanceof Player && KitAPI.getkit(v) == "Fps") {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					WarpsAPI.Ir(v, "Fps");
					v.getInventory().setArmorContents((ItemStack[]) null);
					v.getInventory().clear();
					for (final PotionEffect effect : v.getActivePotionEffects()) {
						v.removePotionEffect(effect.getType());
					}
				}
			}, 1L);
			KitAPI.RemoveKit(v);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					KitAPI.DarSopa(v);
					v.updateInventory();
					WarpsAPI.Ir(v, "Fps");
					KitAPI.setKit(v, "Fps");
				}
			}, 2L);
			((Damageable) v).setHealth(20.0);
		}
	}

	@EventHandler
	public void onMe(final PlayerCommandPreprocessEvent e) {
		if (e.getMessage().toLowerCase().startsWith("/me")) {
			e.getPlayer().sendMessage(Main.perma);
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		e.setJoinMessage((String) null);
		final Player p = e.getPlayer();
		p.getInventory().clear();
		p.getInventory().setArmorContents((ItemStack[]) null);
		KitAPI.darItens(p);
		Scoreboarde.ScoreBoard(p);
		if (p.hasPlayedBefore()) {
			WarpsAPI.Ir(p, "Spawn");
		} else {
			WarpsAPI.Ir(p, "Spawn");
			new BukkitRunnable() {
				public void run() {
					WarpsAPI.Ir(p, "Spawn");
				}
			}.runTaskLater((Plugin) Main.getPlugin(Main.class), 15L);
		}
	}

	@EventHandler
	public void onClick(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) != "Nenhum") {
			return;
		}
		if (p.getItemInHand().getType() == Material.CHEST) {
			Kit.inventariokits(p);
		}
		if (p.getItemInHand().getType() == Material.BOOK) {
			WarpsMenu.inventory(p);
		}
		if (p.getItemInHand().getType() == Material.NAME_TAG) {
			p.chat("/rank");
		}
		if (p.getItemInHand().getType() == Material.MAGMA_CREAM) {
			p.chat("/admin");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(final PlayerDeathEvent e) {
		final Player d = e.getEntity();
		e.setDeathMessage((String) null);
		if (e.getEntity().getKiller() instanceof Player) {
			final Player k = e.getEntity().getKiller();
			final PlayerData dataKiller = DataManager.getPlayerData(k);
			final PlayerData data = DataManager.getPlayerData(d);
			k.sendMessage("§a§lKILL §fVoc\u00ea matou: §a§l" + d.getName());
			k.playSound(k.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
			com.extremepvp.Main.getInstance().getRankManager().killReward(k);
			d.sendMessage("§c§lDEATH §fVoc\u00ea foi morto por §c§l" + k.getName());
			d.playSound(d.getLocation(), Sound.PIG_DEATH, 1.0f, 1.0f);
			dataKiller.setKills(dataKiller.getKills() + 1);
			dataKiller.setStreak(dataKiller.getStreak() + 1);
			data.setDeaths(data.getDeaths() + 1);
			data.setStreak(0);
			if (!com.extremepvp.Main.getInstance().getClanManager().isSameClan(d, k)) {
				com.extremepvp.Main.getInstance().getClanManager().clanKillerReward(k);
				com.extremepvp.Main.getInstance().getClanManager().clanKilledReward(d);
			}
			if (dataKiller.getStreak() == 10) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l10");
			} else if (dataKiller.getStreak() == 15) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l15");
			} else if (dataKiller.getStreak() == 20) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l20");
			} else if (dataKiller.getStreak() == 25) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l25");
			} else if (dataKiller.getStreak() == 30) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l30");
			} else if (dataKiller.getStreak() == 35) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l35");
			} else if (dataKiller.getStreak() == 40) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l40");
			} else if (dataKiller.getStreak() == 45) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l45");
			} else if (dataKiller.getStreak() == 50) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l50");
			} else if (dataKiller.getStreak() == 55) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l55");
			} else if (dataKiller.getStreak() == 60) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l60");
			} else if (dataKiller.getStreak() == 65) {
				Bukkit.broadcastMessage(
						"§c§lKILLSTREAK §e" + k.getName() + "§f alcan\u00e7ou um §4§lKILLSTREAK§f de §a§l65");
			}
		}
	}

	@EventHandler
	public void onRespawn(final PlayerRespawnEvent e) {
		KitAPI.darItens(e.getPlayer());
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		event.setQuitMessage((String) null);
	}

	@EventHandler
	public void onHunger(final FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onWeather(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onSoup(final PlayerInteractEvent e) {
		final Damageable hp;
		final Player p = (Player) (hp = (Damageable) e.getPlayer());
		if (hp.getHealth() != 20.0
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getItemInHand().getTypeId() == 282) {
			p.setHealth((hp.getHealth() + 7.0 > hp.getMaxHealth()) ? hp.getMaxHealth() : (hp.getHealth() + 7.0));
			p.getItemInHand().setType(Material.BOWL);
			if (KitAPI.getkit(p) == "Quickdropper") {
				p.getInventory().removeItem(new ItemStack[] { p.getInventory().getItemInHand() });
			}
		}
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player && e.getCause() != EntityDamageEvent.DamageCause.LAVA) {
			final Player p = (Player) e.getEntity();
			if (KitAPI.getkit(p) == "Nenhum" || KitAPI.getkit(p) == "Challenge" || KitAPI.getkit(p) == "Spawn1v1") {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void entityDamage(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			final Player p = (Player) e.getDamager();
			if (KitAPI.getkit(p) == "Nenhum") {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onItemDrop(final ItemSpawnEvent e) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(),
				(Runnable) new Runnable() {
					@Override
					public void run() {
						e.getEntity().remove();
					}
				}, 100L);
	}

	@EventHandler
	public void onCreatureSpawn(final CreatureSpawnEvent e) {
		if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onExplode(final EntityExplodeEvent e) {
		e.setCancelled(true);
	}
}
