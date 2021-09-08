package tk.imperialz.scoreboard;

import tk.imperialz.Main;
import org.bukkit.scheduler.BukkitRunnable;
import tk.imperialz.apis.KitAPI;
import tk.imperialz.gamer.PlayerData;
import tk.imperialz.gamer.DataManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Scoreboarde
{
    public static void ScoreBoard(final Player p) {
        final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = sb.registerNewObjective("score", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        final FastOfflinePlayer line14 = new FastOfflinePlayer("§a");
        final FastOfflinePlayer line15 = new FastOfflinePlayer("§fMatou:");
        final FastOfflinePlayer line16 = new FastOfflinePlayer("§fMorreu:");
        final FastOfflinePlayer line17 = new FastOfflinePlayer("§fStreak:");
        final FastOfflinePlayer line18 = new FastOfflinePlayer("§5");
        final FastOfflinePlayer line19 = new FastOfflinePlayer("§fKit:");
        final FastOfflinePlayer line20 = new FastOfflinePlayer("§e");
        final FastOfflinePlayer line21 = new FastOfflinePlayer("§fXP:");
        final FastOfflinePlayer line22 = new FastOfflinePlayer("§fLiga:");
        final FastOfflinePlayer line23 = new FastOfflinePlayer("§1");
        final FastOfflinePlayer line24 = new FastOfflinePlayer("§6www.wopemc");
        final Team l14 = sb.registerNewTeam("line14");
        final Team l15 = sb.registerNewTeam("line13");
        final Team l16 = sb.registerNewTeam("line12");
        final Team l17 = sb.registerNewTeam("line11");
        final Team l18 = sb.registerNewTeam("line10");
        final Team l19 = sb.registerNewTeam("line8");
        final Team l20 = sb.registerNewTeam("line7");
        final Team l21 = sb.registerNewTeam("line6");
        final Team l22 = sb.registerNewTeam("line4");
        final Team l23 = sb.registerNewTeam("line3");
        final Team l24 = sb.registerNewTeam("line2");
        final Team l25 = sb.registerNewTeam("line1");
        l14.setSuffix("");
        l15.setSuffix("");
        l16.setSuffix("");
        l17.setSuffix("");
        l18.setSuffix("");
        l19.setSuffix("");
        l20.setSuffix("");
        l21.setSuffix("");
        l22.setSuffix("");
        l23.setSuffix("");
        l24.setSuffix("");
        l25.setSuffix("");
        l15.addPlayer((OfflinePlayer)line15);
        l16.addPlayer((OfflinePlayer)line16);
        l17.addPlayer((OfflinePlayer)line17);
        l18.addPlayer((OfflinePlayer)line18);
        l19.addPlayer((OfflinePlayer)line19);
        l20.addPlayer((OfflinePlayer)line20);
        l21.addPlayer((OfflinePlayer)line22);
        l22.addPlayer((OfflinePlayer)line21);
        l23.addPlayer((OfflinePlayer)line23);
        l25.addPlayer((OfflinePlayer)line24);
        obj.getScore(line14.getName()).setScore(8);
        obj.getScore(line15.getName()).setScore(7);
        obj.getScore(line16.getName()).setScore(6);
        obj.getScore(line17.getName()).setScore(5);
        obj.getScore(line18.getName()).setScore(4);
        obj.getScore(line19.getName()).setScore(3);
        obj.getScore(line23.getName()).setScore(2);
        obj.getScore(line24.getName()).setScore(1);
        p.setScoreboard(sb);
    }
    
    public static void update(final Player p) {
        final PlayerData data = DataManager.getPlayerData(p);
        final Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                if (p.getScoreboard() != null && p.getScoreboard().getObjective("score") != null) {
                    final Team l14 = p.getScoreboard().getTeam("line14");
                    final Team l15 = p.getScoreboard().getTeam("line13");
                    final Team l16 = p.getScoreboard().getTeam("line12");
                    final Team l17 = p.getScoreboard().getTeam("line11");
                    final Team l18 = p.getScoreboard().getTeam("line10");
                    final Team l19 = p.getScoreboard().getTeam("line8");
                    final Team l20 = p.getScoreboard().getTeam("line3");
                    final Team l21 = p.getScoreboard().getTeam("line1");
                    l14.setSuffix("");
                    l15.setSuffix(" §7" + data.getKills());
                    l16.setSuffix(" §7" + data.getDeaths());
                    l17.setSuffix(" §7" + data.getStreak());
                    l18.setSuffix(" ");
                    l19.setSuffix(" §b" + KitAPI.getkit(p));
                    l20.setSuffix("");
                    l21.setSuffix(".com.br   §f");
                }
            }
        });
        th.start();
    }
    
    public static void run() {
        new BukkitRunnable() {
            public void run() {
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                    final Player p = onlinePlayers[i];
                    if (p.getScoreboard().getObjective("score") != null) {
                        p.getScoreboard().getObjective("score").setDisplayName("§6§lWope§f§lMC");
                    }
                    Scoreboarde.update(p);
                }
            }
        }.runTaskTimer(Main.plugin, 0L, 10L);
    }
}
