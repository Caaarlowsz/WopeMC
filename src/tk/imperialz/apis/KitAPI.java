package tk.imperialz.apis;

import tk.imperialz.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.GameMode;
import tk.imperialz.comandos.Build;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import tk.imperialz.warps.WarpsAPI;
import java.util.Random;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class KitAPI implements Listener
{
    public static HashMap<String, String> Kit;
    public static HashMap<Player, Long> run;
    
    static {
        KitAPI.Kit = new HashMap<String, String>();
        KitAPI.run = new HashMap<Player, Long>();
    }
    
    public static void setKit(final Player p, final String kit) {
        KitAPI.Kit.put(p.getName(), kit);
    }
    
    public static String getkit(final Player p) {
        if (KitAPI.Kit.containsKey(p.getName())) {
            return KitAPI.Kit.get(p.getName());
        }
        return "Nenhum";
    }
    
    public static void RemoveKit(final Player p) {
        KitAPI.Kit.remove(p.getName());
    }
    
    public static void TeletransportarJogador(final Player p) {
        final Random dice = new Random();
        final int number = dice.nextInt(3);
        switch (number) {
            case 0: {
                WarpsAPI.Ir(p, "1");
                break;
            }
            case 1: {
                WarpsAPI.Ir(p, "2");
                break;
            }
            case 2: {
                WarpsAPI.Ir(p, "3");
                break;
            }
        }
    }
    
    public static void DarSopa(final Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, APITag.criarItem(p, Material.STONE_SWORD, ChatColor.YELLOW + "§lEspada", new String[1], 1, (short)0));
        p.getInventory().setItem(15, APITag.criarItem(p, Material.BROWN_MUSHROOM, ChatColor.YELLOW + "§lCogumelo", new String[1], 64, (short)0));
        p.getInventory().setItem(14, APITag.criarItem(p, Material.RED_MUSHROOM, ChatColor.RED + "§lCogumelo", new String[1], 64, (short)0));
        p.getInventory().setItem(13, APITag.criarItem(p, Material.BOWL, ChatColor.GRAY + "Pote", new String[1], 64, (short)0));
        for (int i = 1; i < 35; ++i) {
            p.getInventory().addItem(new ItemStack[] { APITag.criarItem(p, Material.MUSHROOM_SOUP, ChatColor.GREEN + "§lSopa", new String[1], 1, (short)0) });
        }
        TeletransportarJogador(p);
    }
    
    public static void darItens(final Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(5, APITag.criarItem(p, Material.CHEST, ChatColor.DARK_PURPLE + "Kits §7(Clique)", new String[1], 1, (short)0));
        p.getInventory().setItem(3, APITag.criarItem(p, Material.BOOK, ChatColor.DARK_PURPLE + "Warps §7(Clique)", new String[1], 1, (short)0));
        RemoveKit(p);
        Build.Build.put(p.getName(), Build.BuildStats.OFF);
        p.setLevel(0);
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(false);
        p.getInventory().setArmorContents((ItemStack[])null);
        final Location l = p.getWorld().getSpawnLocation();
        p.teleport(l);
    }
    
    public static void add(final Player p, final int seconds) {
        final long cooldownLength = System.currentTimeMillis() + seconds * 1000;
        KitAPI.run.remove(p);
        KitAPI.run.put(p, cooldownLength);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                KitAPI.run.remove(p);
            }
        }, (long)(seconds * 20));
    }
    
    public static long cooldown(final Player p) {
        final long cooldownLength = KitAPI.run.get(p);
        final long left = (cooldownLength - System.currentTimeMillis()) / 1000L;
        return left;
    }
    
    public static boolean add(final Player p) {
        return KitAPI.run.containsKey(p);
    }
    
    public static void remove(final Player p) {
        KitAPI.run.remove(p);
    }
    
    public static void setNoArmorDefaultItens(final Player p) {
    }
}
