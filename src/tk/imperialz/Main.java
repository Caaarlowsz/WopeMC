package tk.imperialz;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.imperialz.mysql.MySQLFunctions;
import tk.imperialz.scoreboard.Scoreboarde;
import tk.imperialz.warps.WarpsConfig;
import tk.imperialz.apis.eListeners;
import tk.imperialz.apis.CombatLogListener;
import org.bukkit.event.Listener;
import tk.imperialz.apis.Placa;
import tk.imperialz.automatic.ListenerManager;
import tk.imperialz.automatic.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import tk.imperialz.automatic.ReflectionManager;
import tk.imperialz.managers.Permissions;
import tk.imperialz.mysql.MySQL;
import org.bukkit.plugin.Plugin;
import tk.imperialz.apis.CombatLogManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private CombatLogManager combatLog;
    public static String perma;
    public static Plugin plugin;
    public static Main instancea;
    public static MySQL mysql;
    public Permissions perm;
    public ReflectionManager rm;
    
    static {
        Main.perma = ChatColor.RED + "Voce n\u00e3o possui permiss\u00e3o para usar esse comando.";
    }
    
    public static Plugin getPlugin() {
        return Main.plugin;
    }
    
    public static Main getInstance() {
        return Main.instancea;
    }
    
    public void onEnable() {
        Main.plugin = (Plugin)this;
        Main.instancea = this;
        this.rm = new ReflectionManager();
        this.combatLog = new CombatLogManager();
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
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
        Bukkit.getPluginManager().registerEvents((Listener)new Placa(this), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new CombatLogListener(this.combatLog), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new eListeners(this), (Plugin)this);
        WarpsConfig.getConfig().ConfigEnable((Plugin)this);
        Scoreboarde.run();
    }
    
    private void loadMySQL() {
        Main.mysql = new MySQL("localhost", "test", "root", "");
        MySQLFunctions.CriarTabela();
    }
    
    private void saveStatus() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                    final Player player = onlinePlayers[i];
                    if (player.hasPermission("tk.admin")) {
                        player.sendMessage("§9§lDADOS §fSalvando as informa\u00e7\u00f5es de todos os jogadores...");
                    }
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            final int index = Bukkit.getOnlinePlayers().length;
                            int i = 0;
                            Player[] onlinePlayers;
                            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
                                final Player player = onlinePlayers[j];
                                MySQLFunctions.savePlayer(player);
                                ++i;
                                Thread.sleep(800L);
                            }
                            if (i == index) {
                                Player[] onlinePlayers2;
                                for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, k = 0; k < length2; ++k) {
                                    final Player player2 = onlinePlayers2[k];
                                    if (player2.hasPermission("tk.admin")) {
                                        player2.sendMessage("§4§lRESULTADO §fOs dados foram salvos com sucesso e nenhum erro foi encontrado!");
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }.start();
            }
        }, 0L, 6000L);
    }
    
    public void onDisable() {
        Bukkit.getScheduler().cancelAllTasks();
        MySQL.disconnect();
    }
    
    public static boolean NotInGame(final CommandSender sender) {
        return false;
    }
}
