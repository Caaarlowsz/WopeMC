package com.github.caaarlowsz.wopemc.kitpvp;

import com.github.caaarlowsz.kitpvpapi.KitPvP;
import com.github.caaarlowsz.kitpvpapi.KitPvPAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import tk.imperialz.apis.CombatLogListener;
import tk.imperialz.apis.CombatLogManager;
import tk.imperialz.apis.Placa;
import tk.imperialz.apis.eListeners;
import tk.imperialz.automatic.CommandManager;
import tk.imperialz.automatic.ListenerManager;
import tk.imperialz.automatic.ReflectionManager;
import tk.imperialz.managers.Permissions;
import tk.imperialz.mysql.MySQL;
import tk.imperialz.mysql.MySQLFunctions;
import tk.imperialz.scoreboard.Scoreboarde;
import tk.imperialz.warps.WarpsConfig;

public class WopePvP extends JavaPlugin implements KitPvP {

	@Override
	public void onEnable() {
		super.onEnable();
		KitPvPAPI.setInstance(this);

		// TODO: Remover quando melhorar a classe principal
		this.enable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
		KitPvPAPI.setInstance(null);

		// TODO: Remover quando melhorar a classe principal
		this.disable();
	}

	private CombatLogManager combatLog;
	public static String perma;
	public static Plugin plugin;
	public static WopePvP instancea;
	public static MySQL mysql;
	public Permissions perm;
	public ReflectionManager rm;

	static {
		WopePvP.perma = ChatColor.RED + "Voce n\u00e3o possui permiss\u00e3o para usar esse comando.";
	}

	public static Plugin getPlugin() {
		return WopePvP.plugin;
	}

	public static WopePvP getInstance() {
		return WopePvP.instancea;
	}

	public void enable() {
		WopePvP.plugin = this;
		WopePvP.instancea = this;
		this.rm = new ReflectionManager();
		this.combatLog = new CombatLogManager();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.loadMySQL();
		this.loadListeners();
		this.saveStatus();
		this.perm = new Permissions(this);
	}

	private void loadListeners() {
		this.rm = new ReflectionManager();
		new CommandManager(this);
		ListenerManager.loadListener();
		ListenerManager.loadKits();
		Bukkit.getPluginManager().registerEvents(new Placa(this), this);
		Bukkit.getPluginManager().registerEvents(new CombatLogListener(this.combatLog), this);
		Bukkit.getPluginManager().registerEvents(new eListeners(this), this);
		WarpsConfig.getConfig().ConfigEnable(this);
		Scoreboarde.run();
	}

	private void loadMySQL() {
		WopePvP.mysql = new MySQL("localhost", "test", "root", "");
		MySQLFunctions.CriarTabela();
	}

	private void saveStatus() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(WopePvP.plugin, new Runnable() {
			@Override
			public void run() {
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
					final Player player = onlinePlayers[i];
					if (player.hasPermission("tk.admin")) {
						player.sendMessage("???9???lDADOS ???fSalvando as informa\u00e7\u00f5es de todos os jogadores...");
					}
				}
				new Thread() {
					@Override
					public void run() {
						try {
							final int index = Bukkit.getOnlinePlayers().length;
							int i = 0;
							Player[] onlinePlayers;
							for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length,
									j = 0; j < length; ++j) {
								final Player player = onlinePlayers[j];
								MySQLFunctions.savePlayer(player);
								++i;
								Thread.sleep(800L);
							}
							if (i == index) {
								Player[] onlinePlayers2;
								for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length,
										k = 0; k < length2; ++k) {
									final Player player2 = onlinePlayers2[k];
									if (player2.hasPermission("tk.admin")) {
										player2.sendMessage(
												"???4???lRESULTADO ???fOs dados foram salvos com sucesso e nenhum erro foi encontrado!");
									}
								}
							}
						} catch (Exception ex) {
						}
					}
				}.start();
			}
		}, 0L, 6000L);
	}

	public void disable() {
		Bukkit.getScheduler().cancelAllTasks();
		MySQL.disconnect();
	}

	public static boolean NotInGame(final CommandSender sender) {
		return false;
	}
}
