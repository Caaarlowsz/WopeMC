package tk.imperialz.eventos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;
import tk.imperialz.apis.APITag;
import tk.imperialz.apis.KitAPI;
import tk.imperialz.comandos.Build;

public class Habilidades implements Listener {
	ArrayList<String> timer;
	ArrayList<String> noescape;
	public HashMap<Player, Player> a;
	public HashMap<Player, Long> b;
	public static List<Player> cooldownbk;
	public boolean sendThroughInventory;
	private transient HashMap<ItemStack, Long> monkStaff;
	private ArrayList<String> cool;
	public static WopePvP plugin;
	static ArrayList<String> emhotpotato;
	public static HashMap<String, ItemStack[]> salvararmor;
	public static ArrayList<String> emphantom;
	public long cooldownLenght;
	public static ArrayList<Player> cooldownm;
	public boolean generateGlass;
	public static ArrayList<Player> noExecut;
	public static HashMap<String, Location> oldl;
	public static HashMap<String, String> fighting;
	public HashMap<Integer, ArrayList<Location>> blocks;
	public static HashMap<Player, Location> localizacao;
	public static HashMap<Location, Block> bloco;
	public HashMap<Integer, String[]> players;
	public HashMap<String, Integer> tasks;
	int nextID;
	public static int id1;
	public static int id2;

	static {
		Habilidades.cooldownbk = new ArrayList<Player>();
		Habilidades.emhotpotato = new ArrayList<String>();
		Habilidades.cooldownm = new ArrayList<Player>();
		Habilidades.noExecut = new ArrayList<Player>();
		Habilidades.oldl = new HashMap<String, Location>();
		Habilidades.fighting = new HashMap<String, String>();
		Habilidades.localizacao = new HashMap<Player, Location>();
		Habilidades.bloco = new HashMap<Location, Block>();
	}

	public Habilidades() {
		this.timer = new ArrayList<String>();
		this.noescape = new ArrayList<String>();
		this.a = new HashMap<Player, Player>();
		this.b = new HashMap<Player, Long>();
		this.sendThroughInventory = true;
		this.monkStaff = new HashMap<ItemStack, Long>();
		this.cool = new ArrayList<String>();
		Habilidades.salvararmor = new HashMap<String, ItemStack[]>();
		Habilidades.emphantom = new ArrayList<String>();
		this.cooldownLenght = 0L;
		this.generateGlass = true;
		this.blocks = new HashMap<Integer, ArrayList<Location>>();
		this.players = new HashMap<Integer, String[]>();
		this.tasks = new HashMap<String, Integer>();
		this.nextID = 0;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerHitAnchor(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		final Player a = (Player) e.getDamager();
		if (KitAPI.getkit(p) == "Anchor") {
			p.setVelocity(new Vector());
			p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.instancea,
					(Runnable) new Runnable() {
						@Override
						public void run() {
							p.setVelocity(new Vector());
						}
					}, 1L);
		}
		if (KitAPI.getkit(a) == "Anchor") {
			a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			p.setVelocity(new Vector());
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.instancea,
					(Runnable) new Runnable() {
						@Override
						public void run() {
							p.setVelocity(new Vector());
						}
					}, 1L);
		}
	}

	@EventHandler
	public void aoPoseidon(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		final Block b = p.getLocation().getBlock();
		if (KitAPI.getkit(p) == "Poseidon"
				&& (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3));
		}
	}

	@EventHandler
	public void onPlayerInteract1(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) == "Sniper"
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getInventory().getItemInHand().getType() == Material.BONE) {
			if (KitAPI.add(p)) {
				p.sendMessage("�cVoc\u00ea est\u00e1 em cooldown.");
				return;
			}
			final Vector velo2 = p.getLocation().getDirection().normalize().multiply(100);
			velo2.add(new Vector(Math.random() * 0.0 - 0.0, 0.0, 0.0));
			KitAPI.add(p, 30);
			((Arrow) p.launchProjectile(Arrow.class)).setVelocity(velo2);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�7Voc\u00ea j\u00e1 pode usar novamente.");
				}
			}, 600L);
		}
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow) {
			final Arrow s = (Arrow) e.getDamager();
			final Player damaged = (Player) e.getEntity();
			if (s.getShooter() instanceof Player) {
				final Player shooter = (Player) s.getShooter();
				if (KitAPI.getkit(shooter) == "Sniper") {
					e.setDamage(8.0);
					damaged.getLocation().getWorld().playEffect(damaged.getLocation(), Effect.STEP_SOUND,
							(Object) Material.REDSTONE_WIRE);
					damaged.getEyeLocation().getWorld().playEffect(damaged.getLocation(), Effect.BOW_FIRE, 50000,
							10000);
					damaged.getLocation().getWorld().playEffect(damaged.getLocation(), Effect.BLAZE_SHOOT, 10000,
							10000);
				}
			}
		}
	}

	@EventHandler
	public void onInteract(final PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			final Player k = (Player) e.getRightClicked();
			if (p.getItemInHand().getType().equals((Object) Material.TNT) && KitAPI.getkit(p) == "HotPotato") {
				if (Habilidades.fighting.containsKey(p.getName())) {
					p.sendMessage("�cVoc\u00ea n\u00e3o pode usar em gladiator, mas recebeu efeitos.");
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
				} else {
					if (KitAPI.add(p)) {
						p.sendMessage("�cVoc\u00ea est\u00e1 em cooldown.");
						return;
					}
					KitAPI.add(p, 20);
					Habilidades.emhotpotato.add(k.getName());
					p.sendMessage("�aHotPotato colocada.");
					k.sendMessage("�cVoc\u00ea est\u00e1 com uma HotPotato, tire-a em 5 segundos ou exploda.");
					k.sendMessage("�cClique com o botao direito na hotpotato para tir\u00e1-la.");
					final ItemStack tnt = new ItemStack(Material.TNT);
					final ItemMeta tntmeta = tnt.getItemMeta();
					tntmeta.setDisplayName("�cTNT");
					tnt.setItemMeta(tntmeta);
					k.getInventory().setHelmet(tnt);
					new BukkitRunnable() {
						public void run() {
							if (Habilidades.emhotpotato.contains(k.getName())) {
								k.sendMessage("�cVoc\u00ea ainda est\u00e1 com a TNT, explodindo em 4 segundos.");
								k.sendMessage("�cClique com o botao direito na hotpotato para tir\u00e1-la.");
							}
						}
					}.runTaskLater((Plugin) WopePvP.getInstance(), 0L);
					new BukkitRunnable() {
						public void run() {
							if (Habilidades.emhotpotato.contains(k.getName())) {
								k.sendMessage("�cVoc\u00ea ainda est\u00e1 com a TNT, explodindo em 3 segundos.");
								k.sendMessage("�cClique com o botao direito na hotpotato para tir\u00e1-la.");
							}
						}
					}.runTaskLater((Plugin) WopePvP.getInstance(), 20L);
					new BukkitRunnable() {
						public void run() {
							if (Habilidades.emhotpotato.contains(k.getName())) {
								k.sendMessage("�cVoc\u00ea ainda est\u00e1 com a TNT, explodindo em 2 segundos.");
								k.sendMessage("�cClique com o botao direito na hotpotato para tir\u00e1-la.");
							}
						}
					}.runTaskLater((Plugin) WopePvP.getInstance(), 40L);
					new BukkitRunnable() {
						public void run() {
							if (Habilidades.emhotpotato.contains(k.getName())) {
								k.sendMessage("�cVoc\u00ea ainda est\u00e1 com a TNT, explodindo em 1 segundos.");
								k.sendMessage("�cClique com o botao direito na hotpotato para tir\u00e1-la.");
							}
						}
					}.runTaskLater((Plugin) WopePvP.getInstance(), 60L);
					new BukkitRunnable() {
						public void run() {
							if (Habilidades.emhotpotato.contains(k.getName())) {
								k.getWorld().createExplosion(k.getLocation(), 3.0f, true);
								k.getWorld().playEffect(k.getLocation(), Effect.EXPLOSION_HUGE, 20);
								k.setLastDamage(9999.0);
								Habilidades.emhotpotato.remove(k.getName());
							}
						}
					}.runTaskLater((Plugin) WopePvP.getInstance(), 80L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							p.sendMessage("�7Voc\u00ea j\u00e1 pode usar novamente.");
						}
					}, 500L);
				}
			}
		}
	}

	@EventHandler
	public void onRemoverTNT(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (KitAPI.getkit(p) != "Nenhum" && e.getSlot() == 39
				&& e.getCurrentItem().getType().equals((Object) Material.TNT)
				&& Habilidades.emhotpotato.contains(p.getName())) {
			Habilidades.emhotpotato.remove(p.getName());
			e.setCancelled(true);
			p.getInventory().setHelmet((ItemStack) null);
			p.playSound(p.getLocation(), Sound.CREEPER_HISS, 2.0f, 2.0f);
			p.sendMessage("�aVoc\u00ea desarmou a HotPotato.");
			p.closeInventory();
		}
	}

	@EventHandler
	public void onClick(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player damager = (Player) e.getDamager();
			final Player victim = (Player) e.getEntity();
			if (KitAPI.getkit(damager) == "Reaper") {
				KitAPI.getkit(damager);
				if (damager.getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
					victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3));
				}
			}
		}
	}

	@EventHandler
	public void damage(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (KitAPI.getkit(p) == "Magma" && (e.getCause() == EntityDamageEvent.DamageCause.LAVA
				|| e.getCause() == EntityDamageEvent.DamageCause.FIRE
				|| e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void aoPassar(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		final Material block = p.getLocation().getBlock().getType();
		if (KitAPI.getkit(p) == "Magma" && (block == Material.STATIONARY_WATER || block == Material.WATER)) {
			p.damage(1.0);
		}
	}

	@EventHandler
	public void Fogo(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		final Player s = (Player) e.getDamager();
		if (KitAPI.getkit(s) == "Magma" && s.getInventory().getItemInHand() != null) {
			final Random rand = new Random();
			final int percent = rand.nextInt(100);
			if (percent <= 33) {
				p.setFireTicks(50);
			}
		}
	}

	@EventHandler
	public void onPlayerToggleFly(final PlayerToggleFlightEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) == "Deshzin") {
			if (p.getGameMode() == GameMode.CREATIVE) {
				return;
			}
			e.setCancelled(true);
			p.setFlying(false);
			p.setAllowFlight(false);
			final Location loc = p.getLocation();
			if (!p.isSneaking()) {
				p.setFallDistance(-3.0f);
				final Vector vector = p.getEyeLocation().getDirection();
				vector.multiply(1.3f);
				vector.setY(0.7f);
				p.setVelocity(vector);
			} else {
				p.setFallDistance(-3.0f);
				final Vector vector = p.getEyeLocation().getDirection();
				vector.multiply(1.35f);
				vector.setY(0.5);
				p.setVelocity(vector);
			}
			loc.getWorld().playSound(loc, Sound.ORB_PICKUP, 1.0f, 0.2f);
			p.getWorld().playEffect(p.getLocation(), Effect.COLOURED_DUST, 80);
		}
	}

	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) == "Deshzin") {
			if (p.getGameMode() == GameMode.CREATIVE) {
				return;
			}
			if (p.isOnGround() && !p.getAllowFlight()) {
				p.setAllowFlight(true);
			}
		}
	}

	@EventHandler
	public void AoTeleport(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
			final Arrow arrow = (Arrow) e.getDamager();
			final Player p = (Player) arrow.getShooter();
			p.getLocation().distance(e.getEntity().getLocation());
			if (e.getEntity() instanceof Player && KitAPI.getkit(p) == "Teleporter") {
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
				p.teleport(e.getEntity().getLocation());
			}
		}
	}

	@EventHandler
	public void snowball(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
			final Snowball s = (Snowball) e.getDamager();
			if (s.getShooter() instanceof Player) {
				final Player shooter = (Player) s.getShooter();
				if (KitAPI.getkit(shooter) == "Switcher") {
					final Location shooterLoc = shooter.getLocation();
					shooter.teleport(e.getEntity().getLocation());
					e.getEntity().teleport(shooterLoc);
					shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.ENDER_SIGNAL, 10);
					shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.EXTINGUISH, 10);
					shooter.getWorld().playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.2f);
				}
			}
		}
	}

	@EventHandler
	public void aogigante(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p).equalsIgnoreCase("Phantom")
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getItemInHand().getType() == Material.FEATHER) {
			if (KitAPI.add(p)) {
				p.sendMessage("�cVoc\u00ea est\u00e1 em cooldown");
				return;
			}
			Habilidades.salvararmor.put(p.getName(), p.getInventory().getArmorContents());
			p.updateInventory();
			Habilidades.emphantom.add(p.getName());
			p.setAllowFlight(true);
			p.setFlying(true);
			p.getInventory().setArmorContents((ItemStack[]) null);
			final ItemStack Peito = new ItemStack(Material.LEATHER_CHESTPLATE);
			final LeatherArmorMeta kPeito = (LeatherArmorMeta) Peito.getItemMeta();
			kPeito.setDisplayName("�cPeitoral");
			kPeito.setColor(Color.GREEN);
			Peito.setItemMeta((ItemMeta) kPeito);
			final ItemStack Cal\u00e7a = new ItemStack(Material.LEATHER_LEGGINGS);
			final LeatherArmorMeta kCa\u00e7a = (LeatherArmorMeta) Cal\u00e7a.getItemMeta();
			kCa\u00e7a.setDisplayName("�cCal\u00e7a");
			kCa\u00e7a.setColor(Color.GREEN);
			Cal\u00e7a.setItemMeta((ItemMeta) kCa\u00e7a);
			final ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
			final LeatherArmorMeta kBota = (LeatherArmorMeta) Bota.getItemMeta();
			kBota.setDisplayName("�cBotas");
			kBota.setColor(Color.GREEN);
			Bota.setItemMeta((ItemMeta) kBota);
			final ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
			final LeatherArmorMeta kCasapete = (LeatherArmorMeta) Capacete.getItemMeta();
			kCasapete.setDisplayName("�cCapacete");
			kCasapete.setColor(Color.GREEN);
			Capacete.setItemMeta((ItemMeta) kCasapete);
			p.getInventory().setChestplate(Peito);
			p.getInventory().setLeggings(Cal\u00e7a);
			p.getInventory().setHelmet(Capacete);
			p.getInventory().setBoots(Bota);
			p.updateInventory();
			KitAPI.add(p, 20);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�cSeu phantom est\u00e1 acabando em �b5�7s");
				}
			}, 0L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�cSeu phantom est\u00e1 acabando em �b4�7s");
				}
			}, 20L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�cSeu phantom est\u00e1 acabando em �b3�7s");
				}
			}, 40L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�cSeu phantom est\u00e1 acabando em �b2�7s");
				}
			}, 60L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�cSeu phantom est\u00e1 acabando em �b1�7s");
				}
			}, 80L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�7O �bfly acabou");
					Habilidades.emphantom.remove(p.getName());
					p.getInventory().setArmorContents((ItemStack[]) Habilidades.salvararmor.get(p.getName()));
					p.updateInventory();
					p.setAllowFlight(false);
					p.setFlying(false);
					p.closeInventory();
					p.getInventory().remove(Material.LEATHER_BOOTS);
					p.getInventory().remove(Material.LEATHER_CHESTPLATE);
					p.getInventory().remove(Material.LEATHER_HELMET);
					p.getInventory().remove(Material.LEATHER_LEGGINGS);
					p.updateInventory();
				}
			}, 100L);
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) WopePvP.getInstance(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("�aVoce saiu de cooldown.");
				}
			}, 400L);
		}
	}

	@EventHandler
	public void aoinv(final InventoryClickEvent e) {
		try {
			final Player p = (Player) e.getWhoClicked();
			if (Habilidades.emphantom.contains(p.getName())
					&& e.getSlotType().equals((Object) InventoryType.SlotType.ARMOR)) {
				e.setCancelled(true);
			}
		} catch (Exception ex) {
		}
	}

	@EventHandler
	public void dano(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (KitAPI.getkit(d) == "Critical") {
				final Random r = new Random();
				final int c = r.nextInt(100);
				if (c <= 30) {
					e.setDamage(e.getDamage() + 2.0);
					p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, (Object) Material.WATER, 100);
					p.sendMessage("�cVoc\u00ea recebeu um golpe critico de " + d.getName());
					d.sendMessage("�cVoc\u00ea deu um golpe critico em " + p.getName());
				}
			}
		}
	}

	@EventHandler
	public void Pescar(final PlayerFishEvent e) {
		final Player p = e.getPlayer();
		if (e.getCaught() instanceof Player && KitAPI.getkit(p) == "Fisherman") {
			final Player t = (Player) e.getCaught();
			t.teleport((Entity) p);
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onLauncher(final PlayerFishEvent e) {
		final Player p = e.getPlayer();
		if (e.getCaught() instanceof Player && KitAPI.getkit(p) == "Launcher") {
			final Player t = (Player) e.getCaught();
			if (this.hasCooldown()) {
				p.sendMessage("�cFaltam " + this.cooldownTimeRemaining() + " para o cooldown acabar!");
				return;
			}
			t.setVelocity(t.getVelocity().setY(1.0));
			this.addCooldown(p, 30);
		}
	}

	public void addCooldown(final Player player, final int seconds) {
		this.cooldownLenght = System.currentTimeMillis() + seconds * 1000;
	}

	public String cooldownTimeRemaining() {
		final long faltam = (this.cooldownLenght - System.currentTimeMillis()) / 1000L;
		if (faltam < 60L) {
			return String.valueOf(String.valueOf(faltam)) + " segundos restantes";
		}
		return String.valueOf(String.valueOf(faltam)) + " minutos restantes";
	}

	public boolean hasCooldown() {
		return this.cooldownLenght > System.currentTimeMillis();
	}

	@EventHandler
	public void Andar(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND
				|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SANDSTONE)
				&& KitAPI.getkit(p) == "Camel") {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
		}
	}

	@EventHandler
	public void Bater(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player t = (Player) e.getDamager();
			if (KitAPI.getkit(t) == "Viper" && t.getItemInHand().getType() == Material.STONE_SWORD
					&& new Random().nextInt(100) <= 25) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
			}
		}
	}

	@EventHandler
	public void pular(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (p.getItemInHand().getType() == Material.FIREWORK && KitAPI.getkit(p) == "Kangaroo") {
			event.setCancelled(true);
			final Vector vector = p.getEyeLocation().getDirection();
			if (!this.noescape.contains(p.getName())) {
				if (!this.timer.contains(p.getName())) {
					this.timer.add(p.getName());
					if (!p.isSneaking()) {
						p.setFallDistance(-1.0f);
						vector.multiply(0.5f);
						vector.setY(1.0);
						p.setVelocity(vector);
					} else {
						p.setFallDistance(-1.0f);
						vector.multiply(1.5f);
						vector.setY(0.5);
						p.setVelocity(vector);
					}
				}
			} else if (!this.timer.contains(p.getName())) {
				this.timer.add(p.getName());
				p.setFallDistance(0.0f);
				vector.multiply(0.0f);
				vector.setY(0.0);
				p.setVelocity(vector);
			}
		}
	}

	@EventHandler
	public void removertimer(final PlayerMoveEvent event) {
		final Player p = event.getPlayer();
		if (this.timer.contains(p.getName())) {
			final Block b = p.getLocation().getBlock();
			if (b.getType() != Material.AIR || b.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
				this.timer.remove(p.getName());
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerStomp(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (KitAPI.getkit(p) == "Stomper") {
			for (final Entity ent : p.getNearbyEntities(3.0, 3.0, 3.0)) {
				if (!(ent instanceof Player)) {
					continue;
				}
				final Player plr = (Player) ent;
				if (e.getDamage() <= 4.0) {
					e.setCancelled(true);
					return;
				}
				if (plr.isSneaking() || KitAPI.getkit(plr) == "AntiStomper") {
					plr.damage(6.0, (Entity) p);
				} else {
					plr.damage(999.0, (Entity) p);
					plr.getKiller();
				}
			}
			e.setDamage(4.0);
		}
	}

	@EventHandler
	public void aoIronMan(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) == "IronMan"
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getItemInHand().getType() == Material.WORKBENCH) {
			p.openWorkbench(p.getLocation(), true);
		}
	}

	@EventHandler
	public void aoMatar(final PlayerDeathEvent e) {
		final Player matou = e.getEntity().getKiller();
		if (!(e.getEntity().getKiller() instanceof Player)) {
			return;
		}
		if (KitAPI.getkit(matou) == "IronMan" && e.getEntity().getKiller() instanceof Player) {
			final ItemStack ferro = new ItemStack(Material.IRON_INGOT);
			matou.getInventory().addItem(new ItemStack[] { ferro });
			matou.updateInventory();
		}
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent event) {
		final Entity e = event.getEntity();
		if (e instanceof Player) {
			final Player player = (Player) e;
			if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL
					&& KitAPI.getkit(player) == "Kangaroo" && event.getDamage() >= 12.0) {
				event.setDamage(6.0);
			}
		}
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (KitAPI.getkit(p) == "Turtle" && p.isSneaking()
				&& (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
						|| e.getCause() == EntityDamageEvent.DamageCause.CONTACT
						|| e.getCause() == EntityDamageEvent.DamageCause.CUSTOM
						|| e.getCause() == EntityDamageEvent.DamageCause.DROWNING
						|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK
						|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
						|| e.getCause() == EntityDamageEvent.DamageCause.FALL
						|| e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK
						|| e.getCause() == EntityDamageEvent.DamageCause.FIRE
						|| e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
						|| e.getCause() == EntityDamageEvent.DamageCause.LAVA
						|| e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING
						|| e.getCause() == EntityDamageEvent.DamageCause.MAGIC
						|| e.getCause() == EntityDamageEvent.DamageCause.MELTING
						|| e.getCause() == EntityDamageEvent.DamageCause.POISON
						|| e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE
						|| e.getCause() == EntityDamageEvent.DamageCause.STARVATION
						|| e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION
						|| e.getCause() == EntityDamageEvent.DamageCause.THORNS
						|| e.getCause() == EntityDamageEvent.DamageCause.VOID
						|| e.getCause() == EntityDamageEvent.DamageCause.WITHER)) {
			e.setDamage(1.0);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerTurtleDamage(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getDamager();
		if (!p.isSneaking()) {
			return;
		}
		if (KitAPI.getkit(p) == "Turtle") {
			e.setCancelled(true);
			p.sendMessage("�cVoce nao pode bater enquanto estiver de shift");
		}
	}

	@EventHandler
	public void a(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
		if (paramEntityDamageByEntityEvent.getDamager() instanceof Player
				&& paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
			final Player localPlayer1 = (Player) paramEntityDamageByEntityEvent.getDamager();
			final Player localPlayer2 = (Player) paramEntityDamageByEntityEvent.getEntity();
			if (KitAPI.getkit(localPlayer1) == "Ninja") {
				this.a.put(localPlayer1, localPlayer2);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						Habilidades.cooldownbk.remove(localPlayer1);
					}
				}, 200L);
			}
		}
	}

	@EventHandler
	public void a(final PlayerToggleSneakEvent paramPlayerToggleSneakEvent) {
		final Player localPlayer1 = paramPlayerToggleSneakEvent.getPlayer();
		if (paramPlayerToggleSneakEvent.isSneaking() && KitAPI.getkit(localPlayer1) == "Ninja"
				&& this.a.containsKey(localPlayer1)) {
			final Player localPlayer2 = this.a.get(localPlayer1);
			if (localPlayer2 != null && !localPlayer2.isDead()) {
				String str = null;
				if (this.b.get(localPlayer1) != null) {
					final long l = this.b.get(localPlayer1) - System.currentTimeMillis();
					final DecimalFormat localDecimalFormat = new DecimalFormat("##");
					final int i = (int) l / 1000;
					str = localDecimalFormat.format(i);
				}
				if (this.b.get(localPlayer1) == null || this.b.get(localPlayer1) < System.currentTimeMillis()) {
					if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 100.0) {
						if (Habilidades.noExecut.contains(localPlayer1)) {
							localPlayer2.sendMessage("�cEsse player est\u00e1 em um gladiator");
							return;
						}
						if (Habilidades.noExecut.contains(localPlayer2)) {
							localPlayer1.sendMessage("�cHabilidade proibida em gladiator");
							return;
						}
						localPlayer1.teleport(localPlayer2.getLocation());
						this.b.put(localPlayer1, System.currentTimeMillis() + 10000L);
					} else {
						localPlayer1.sendMessage("�cN\u00e3o \u00e9 poss\u00edvel se teletransportar.");
					}
				} else {
					localPlayer1.sendMessage("�cVoc\u00ea est\u00e1 em cooldown de: " + str + " segundos.");
				}
			}
		}
	}

	@EventHandler
	public void onSlow(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (KitAPI.getkit(d) == "Snail") {
				final Random r = new Random();
				final int rand = r.nextInt(100);
				if (rand >= 67) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1));
				}
			}
		}
	}

	@EventHandler
	public void monkNeero(final PlayerInteractEntityEvent event) {
		final ItemStack item = event.getPlayer().getItemInHand();
		final Player Player2 = event.getPlayer();
		if (KitAPI.getkit(Player2) == "Monk" && Player2.getItemInHand().getType() == Material.BLAZE_ROD) {
			long lastUsed = 0L;
			if (this.monkStaff.containsKey(item)) {
				lastUsed = this.monkStaff.get(item);
			}
			if (lastUsed + 15000L > System.currentTimeMillis()) {
				event.getPlayer().sendMessage(String.format("�cVoc\u00ea est\u00e1 em cooldown de ",
						-(System.currentTimeMillis() - (lastUsed + 15000L)) / 1000L));
			} else {
				final PlayerInventory inv = ((Player) event.getRightClicked()).getInventory();
				final int slot = new Random().nextInt(this.sendThroughInventory ? 36 : 9);
				ItemStack replaced = inv.getItemInHand();
				if (replaced == null) {
					replaced = new ItemStack(0);
				}
				ItemStack replacer;
				if ((replacer = inv.getItem(slot)) == null) {
					replacer = new ItemStack(0);
				}
				inv.setItemInHand(replacer);
				inv.setItem(slot, replaced);
				this.monkStaff.put(item, System.currentTimeMillis());
			}
		}
	}

	@EventHandler
	public void az(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
		if (paramEntityDamageByEntityEvent.getDamager() instanceof Player
				&& paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
			final Player localPlayer1 = (Player) paramEntityDamageByEntityEvent.getDamager();
			final Player localPlayer2 = (Player) paramEntityDamageByEntityEvent.getEntity();
			if (KitAPI.getkit(localPlayer1) == "Ajnin") {
				this.a.put(localPlayer1, localPlayer2);
			}
		}
	}

	@EventHandler
	public void a2(final PlayerToggleSneakEvent paramPlayerToggleSneakEvent) {
		final Player localPlayer1 = paramPlayerToggleSneakEvent.getPlayer();
		if (paramPlayerToggleSneakEvent.isSneaking() && KitAPI.getkit(localPlayer1) == "Ajnin"
				&& this.a.containsKey(localPlayer1)) {
			final Player localPlayer2 = this.a.get(localPlayer1);
			if (localPlayer2 != null && !localPlayer2.isDead()) {
				String str = null;
				if (this.b.get(localPlayer1) != null) {
					final long l = this.b.get(localPlayer1) - System.currentTimeMillis();
					final DecimalFormat localDecimalFormat = new DecimalFormat("##");
					final int i = (int) l / 1000;
					str = localDecimalFormat.format(i);
				}
				if (this.b.get(localPlayer1) == null || this.b.get(localPlayer1) < System.currentTimeMillis()) {
					if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 50.0) {
						if (Habilidades.noExecut.contains(localPlayer1)) {
							localPlayer2.sendMessage("�7Esse player est\u00e1 em um gladiator");
							return;
						}
						if (Habilidades.noExecut.contains(localPlayer2)) {
							localPlayer1.sendMessage("�7Habilidade proibida em gladiator");
							return;
						}
						localPlayer2.teleport(localPlayer1.getLocation());
						this.b.put(localPlayer1, System.currentTimeMillis() + 10000L);
					} else {
						localPlayer1.sendMessage("�cO jogador est\u00e1 muito longe.");
					}
				} else {
					localPlayer1.sendMessage("�cVoc\u00ea ainda est\u00e1 em cooldown de: " + str + " segundos.");
				}
			}
		}
	}

	@EventHandler
	public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (event.getRightClicked() instanceof Player) {
			final Player r = (Player) event.getRightClicked();
			if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.getkit(p) == "Gladiator") {
				event.setCancelled(true);
				final Location loc = new Location(p.getWorld(), (double) p.getLocation().getBlockX(),
						(double) (p.getLocation().getBlockY() + 70), (double) p.getLocation().getBlockZ());
				Habilidades.localizacao.put(p, loc);
				Habilidades.localizacao.put(r, loc);
				final Location loc2 = new Location(p.getWorld(), (double) (p.getLocation().getBlockX() + 8),
						(double) (p.getLocation().getBlockY() + 73), (double) (p.getLocation().getBlockZ() + 8));
				final Location loc3 = new Location(p.getWorld(), (double) (p.getLocation().getBlockX() - 8),
						(double) (p.getLocation().getBlockY() + 73), (double) (p.getLocation().getBlockZ() - 8));
				if (Habilidades.fighting.containsKey(p.getName()) || Habilidades.fighting.containsKey(r.getName())) {
					event.setCancelled(true);
					return;
				}
				final Integer currentID = this.nextID;
				++this.nextID;
				final ArrayList<String> list = new ArrayList<String>();
				list.add(p.getName());
				list.add(r.getName());
				this.players.put(currentID, list.toArray(new String[1]));
				Habilidades.oldl.put(p.getName(), p.getLocation());
				Habilidades.oldl.put(r.getName(), r.getLocation());
				if (this.generateGlass) {
					final List<Location> cuboid = new ArrayList<Location>();
					cuboid.clear();
					for (int bX = -10; bX <= 10; ++bX) {
						for (int bZ = -10; bZ <= 10; ++bZ) {
							for (int bY = -1; bY <= 10; ++bY) {
								final Block b = loc.clone().add((double) bX, (double) bY, (double) bZ).getBlock();
								if (!b.isEmpty()) {
									event.setCancelled(true);
									p.sendMessage("�cVoc\u00ea n\u00e3o pode puxar nesse local");
									return;
								}
								if (bY == 10) {
									cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
								} else if (bY == -1) {
									cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
								} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
									cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
								}
							}
						}
					}
					for (final Location loc4 : cuboid) {
						loc4.getBlock().setType(Material.GLASS);
						Habilidades.bloco.put(loc4, loc4.getBlock());
					}
					loc2.setYaw(135.0f);
					p.teleport(loc2);
					loc3.setYaw(-45.0f);
					r.teleport(loc3);
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
					r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
					p.getInventory().remove(Material.IRON_FENCE);
					r.getInventory().remove(Material.IRON_FENCE);
					p.sendMessage("�fItem do �c�lGLADIATOR REMOVIDO");
					p.sendMessage("�fCaso mate o player ele ir\u00e1 voltar para seu inventario");
					Build.Build.put(p.getName(), Build.BuildStats.ON);
					Build.Build.put(r.getName(), Build.BuildStats.ON);
					Habilidades.noExecut.add(p);
					Habilidades.noExecut.add(r);
					Habilidades.fighting.put(p.getName(), r.getName());
					Habilidades.fighting.put(r.getName(), p.getName());
					p.getInventory().setItem(2, APITag.criarItem(p, Material.COBBLESTONE, ChatColor.GREEN + "Blocos",
							new String[1], 16, (short) 0));
					Habilidades.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(),
							(Runnable) new Runnable() {
								@Override
								public void run() {
									if (Habilidades.fighting.containsKey(p.getName())
											&& Habilidades.fighting.get(p.getName()).equalsIgnoreCase(r.getName())
											&& Habilidades.fighting.containsKey(r.getName())) {
										Habilidades.fighting.get(r.getName()).equalsIgnoreCase(p.getName());
									}
								}
							}, 2400L);
					Habilidades.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(),
							(Runnable) new Runnable() {
								@Override
								public void run() {
									if (Habilidades.fighting.containsKey(p.getName())
											&& Habilidades.fighting.get(p.getName()).equalsIgnoreCase(r.getName())
											&& Habilidades.fighting.containsKey(r.getName())
											&& Habilidades.fighting.get(r.getName()).equalsIgnoreCase(p.getName())) {
										Habilidades.fighting.remove(p.getName());
										Habilidades.fighting.remove(r.getName());
										Habilidades.noExecut.remove(p);
										Habilidades.noExecut.remove(r);
										p.teleport((Location) Habilidades.oldl.get(p.getName()));
										r.teleport((Location) Habilidades.oldl.get(r.getName()));
										Habilidades.oldl.remove(p.getName());
										Habilidades.oldl.remove(r.getName());
										final Location loc = Habilidades.localizacao.get(p);
										final List<Location> cuboid = new ArrayList<Location>();
										for (int bX = -10; bX <= 10; ++bX) {
											for (int bZ = -10; bZ <= 10; ++bZ) {
												for (int bY = -1; bY <= 10; ++bY) {
													if (bY == 10) {
														cuboid.add(
																loc.clone().add((double) bX, (double) bY, (double) bZ));
													} else if (bY == -1) {
														cuboid.add(
																loc.clone().add((double) bX, (double) bY, (double) bZ));
													} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
														cuboid.add(
																loc.clone().add((double) bX, (double) bY, (double) bZ));
													}
												}
											}
										}
										for (final Location loc2 : cuboid) {
											loc2.getBlock().setType(Material.AIR);
											Habilidades.bloco.get(loc2).setType(Material.AIR);
										}
									}
								}
							}, 100000L);
				}
			}
		}
	}

	@EventHandler
	public void onSpongePlace(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() != Material.COBBLESTONE) {
			return;
		}
		final int spongesleft = p.getItemInHand().getAmount();
		e.setCancelled(true);
		final Location placed = e.getBlock().getLocation();
		final World w = placed.getWorld();
		final double x = placed.getX();
		final double y = placed.getY();
		final double z = placed.getZ();
		final Location sponge = new Location(w, x, y, z);
		final Material block = e.getBlockReplacedState().getType();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				sponge.getBlock().setType(Material.COBBLESTONE);
			}
		}, 1L);
		if (p.getItemInHand().getAmount() == 1) {
			p.setItemInHand(new ItemStack(Material.AIR));
		}
		p.getItemInHand().setAmount(spongesleft - 1);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				sponge.getBlock().setType(block);
			}
		}, 250L);
	}

	@EventHandler
	public void onPlayerInteractGlad(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.getkit(p) == "Gladiator") {
			e.setCancelled(true);
			p.updateInventory();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlyaerInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS
				&& e.getPlayer().getGameMode() != GameMode.CREATIVE
				&& Habilidades.fighting.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.GLASS);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					if (Habilidades.fighting.containsKey(e.getPlayer().getName())) {
						e.getClickedBlock().setType(Material.GLASS);
					}
				}
			}, 30L);
		}
	}

	@EventHandler
	public void onKill(final PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			final Player k = e.getEntity().getKiller();
			if (KitAPI.getkit(k) == "Barbarian") {
				if (k.getItemInHand().getType() == Material.WOOD_SWORD) {
					k.setItemInHand(new ItemStack(Material.STONE_SWORD));
					k.updateInventory();
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f1.");
				} else if (k.getItemInHand().getType() == Material.STONE_SWORD) {
					k.setItemInHand(new ItemStack(Material.STONE_SWORD));
					k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f2.");
				} else if (k.getItemInHand().getType() == Material.IRON_SWORD) {
					k.setItemInHand(new ItemStack(Material.IRON_SWORD));
					k.getItemInHand().removeEnchantment(Enchantment.ARROW_FIRE);
					k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					k.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f3.");
				} else if (k.getItemInHand().getType() == Material.IRON_SWORD) {
					k.setItemInHand(new ItemStack(Material.IRON_SWORD));
					k.getItemInHand().removeEnchantment(Enchantment.ARROW_FIRE);
					k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					k.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 2);
					k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 2);
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f4.");
				} else if (k.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					k.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f5.");
				} else if (k.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					k.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
					k.getItemInHand().removeEnchantment(Enchantment.ARROW_FIRE);
					k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					k.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
					k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
					k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					k.sendMessage("�aVoc\u00ea agora subiu para o level �f6.");
				} else if (k.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if (k.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
						final int lvl = k.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
						if (lvl + 1 <= 10) {
							k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
							k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, lvl + 1);
							k.updateInventory();
							k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
							if (lvl + 1 == 2) {
								k.sendMessage("�aVoc\u00ea agora subiu para o level �f7.");
							} else if (lvl + 1 == 3) {
								k.sendMessage("�aVoc\u00ea agora subiu para o level �f8.");
							} else if (lvl + 1 == 4) {
								k.sendMessage("�aVoc\u00ea agora subiu para o level �f9.");
							} else if (lvl + 1 == 5) {
								k.sendMessage("�aVoc\u00ea agora subiu para o level �f10.");
							}
						} else {
							k.sendMessage(
									"�a Ops, voc\u00ea acabou atingindo o n\u00edvel m\u00e1ximo! N\u00e3o \u00e9 mais poss\u00edvel upar!");
						}
					} else {
						k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
						k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
						k.sendMessage("�aVoc\u00ea agora subiu para o level �f4.");
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeft(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		if (Habilidades.fighting.containsKey(p.getName())) {
			final Player t = Bukkit.getServer().getPlayer((String) Habilidades.fighting.get(p.getName()));
			Habilidades.fighting.remove(t.getName());
			Habilidades.fighting.remove(p.getName());
			Habilidades.noExecut.remove(p);
			Habilidades.noExecut.remove(t);
			final ItemStack Item = new ItemStack(Material.IRON_FENCE);
			final ItemMeta kItem = Item.getItemMeta();
			kItem.setDisplayName("�a" + KitAPI.getkit(p));
			Item.setItemMeta(kItem);
			Habilidades.fighting.remove(t.getName());
			Habilidades.fighting.remove(p.getName());
			Habilidades.noExecut.remove(p);
			Habilidades.noExecut.remove(t);
			if (KitAPI.getkit(p) == "Gladiator") {
				p.getInventory().setItem(1, Item);
				p.getInventory().setItem(2, new ItemStack(Material.COBBLESTONE, 16));
			}
			if (KitAPI.getkit(t) == "Gladiator") {
				t.getInventory().setItem(1, Item);
				t.getInventory().setItem(2, new ItemStack(Material.COBBLESTONE, 16));
			}
			final Location old = Habilidades.oldl.get(t.getName());
			t.teleport(old);
			Bukkit.getScheduler().cancelTask(Habilidades.id1);
			Bukkit.getScheduler().cancelTask(Habilidades.id2);
			t.removePotionEffect(PotionEffectType.WITHER);
			final Location loc = Habilidades.localizacao.get(p);
			final List<Location> cuboid = new ArrayList<Location>();
			for (int bX = -10; bX <= 10; ++bX) {
				for (int bZ = -10; bZ <= 10; ++bZ) {
					for (int bY = -1; bY <= 10; ++bY) {
						if (bY == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						}
					}
				}
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				Habilidades.bloco.get(loc2).setType(Material.AIR);
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				Habilidades.bloco.get(loc2).setType(Material.AIR);
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				Habilidades.bloco.get(loc2).setType(Material.AIR);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (KitAPI.getkit(p) == "Thor"
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			final ItemStack hand = p.getItemInHand();
			if (hand.getType() == Material.WOOD_AXE) {
				if (this.cool.contains(p.getName())) {
					p.sendMessage("�cVoc\u00ea ainda est\u00e1 em cooldown!");
				} else {
					final Location loc = p.getTargetBlock(new HashSet<Byte>(), 20).getLocation();
					p.getWorld().strikeLightning(loc);
					this.cool.add(p.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(WopePvP.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							Habilidades.this.cool.remove(p.getName());
							p.sendMessage("�aJ\u00e1 pode usar novamente!");
						}
					}, 80L);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeathGladiator(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if (Habilidades.fighting.containsKey(p.getName())) {
			final Player k = Bukkit.getServer().getPlayer((String) Habilidades.fighting.get(p.getName()));
			final Location old = Habilidades.oldl.get(p.getName());
			k.teleport(old);
			Bukkit.getScheduler().cancelTask(Habilidades.id1);
			Bukkit.getScheduler().cancelTask(Habilidades.id2);
			k.removePotionEffect(PotionEffectType.WITHER);
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
			final ItemStack Item = new ItemStack(Material.IRON_FENCE);
			final ItemMeta kItem = Item.getItemMeta();
			kItem.setDisplayName("�a" + KitAPI.getkit(p));
			Item.setItemMeta(kItem);
			Habilidades.fighting.remove(k.getName());
			Habilidades.fighting.remove(p.getName());
			Habilidades.noExecut.remove(p);
			Habilidades.noExecut.remove(k);
			if (KitAPI.getkit(p) == "Gladiator") {
				p.getInventory().setItem(2, Item);
			}
			if (KitAPI.getkit(k) == "Gladiator") {
				k.getInventory().setItem(2, Item);
			}
			Build.Build.put(k.getName(), Build.BuildStats.OFF);
			Build.Build.put(p.getName(), Build.BuildStats.OFF);
			final Location loc = Habilidades.localizacao.get(p);
			final List<Location> cuboid = new ArrayList<Location>();
			cuboid.clear();
			for (int bX = -10; bX <= 10; ++bX) {
				for (int bZ = -10; bZ <= 10; ++bZ) {
					for (int bY = -1; bY <= 10; ++bY) {
						if (bY == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						}
					}
				}
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				if (Habilidades.bloco.containsKey(loc2)) {
					Habilidades.bloco.get(loc2).setType(Material.AIR);
				}
			}
		}
	}

	@EventHandler
	public void onIasnteract(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (KitAPI.getkit(p) == "Specialist"
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK
						|| event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
				&& p.getItemInHand().getType() == Material.BOOK) {
			p.openEnchanting((Location) null, true);
		}
	}

	@EventHandler
	public void onVampire(final EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player killed = (Player) event.getEntity();
			if (killed.getKiller() instanceof Player) {
				final Player player = event.getEntity().getKiller();
				if (KitAPI.getkit(player) == "Specialist") {
					player.setLevel(1);
				}
			}
		}
	}

	@EventHandler
	public void processocommand(final PlayerCommandPreprocessEvent e) {
		final Player p = e.getPlayer();
		if (Habilidades.noExecut.contains(p) && !p.hasPermission("tk.admin")) {
			e.setCancelled(true);
			p.sendMessage("�cSem comandos em gladiator.");
		}
	}
}
