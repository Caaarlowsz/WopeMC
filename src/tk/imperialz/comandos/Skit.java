package tk.imperialz.comandos;

import java.util.Iterator;
import tk.imperialz.apis.KitAPI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Skit implements Listener, CommandExecutor
{
    public static String setedkit;
    public HashMap<String, ItemStack[]> kit;
    public HashMap<String, ItemStack[]> armadura;
    
    public Skit() {
        this.kit = new HashMap<String, ItemStack[]>();
        this.armadura = new HashMap<String, ItemStack[]>();
    }
    
    public boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String CommandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVoc\u00ea n\u00e3o \u00e9 um jogador.");
            return true;
        }
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("skit") && p.hasPermission("kitpvp.skit") && args.length == 0) {
            p.sendMessage("§b§lSKIT §fUtilize: /skit <criar | aplicar> (nome) [raio]");
            return true;
        }
        if (!args[0].equalsIgnoreCase("criar")) {
            if (args[0].equalsIgnoreCase("aplicar")) {
                if (args.length <= 2) {
                    p.sendMessage("§b§lSKIT §fUtilize: /skit <aplicar> (kit) [raio]");
                    return true;
                }
                final String kitname = args[1];
                if (!this.kit.containsKey(kitname) && !this.armadura.containsKey(kitname)) {
                    p.sendMessage("§4§lERRO §fO §3§lKIT §7(§f" + kitname + "§7) §fn\u00e3o foi §c§lENCONTRADO§7!");
                    return true;
                }
                if (this.isInt(args[2])) {
                    final int raio = Integer.parseInt(args[2]);
                    for (final Entity entidades : p.getNearbyEntities((double)raio, (double)raio, (double)raio)) {
                        if (!(entidades instanceof Player)) {
                            continue;
                        }
                        final Player plr = (Player)entidades;
                        plr.getInventory().setArmorContents((ItemStack[])this.armadura.get(kitname));
                        plr.getInventory().setContents((ItemStack[])this.kit.get(kitname));
                        KitAPI.setKit(plr, kitname);
                        KitAPI.setKit(p, kitname);
                    }
                    p.sendMessage("§b§lSKIT §fO §3§lKIT §7(§c" + kitname + "§7), §fseu §e§lINVENT\u00c1RIO§f, e §2§lARMADURAS §fforam §b§lAPLICADOS §Fpara todos os jogadores em um §6§lRAIO §fde §7(§6" + raio + "§7) §fblocos.");
                    return true;
                }
            }
            else {
                p.sendMessage("§e§lPERMISSAO §fVoc\u00ea n\u00e3o possui §4§lPERMISSAO §fpara executar este §3§lCOMANDO.");
            }
            return true;
        }
        if (args.length == 1) {
            p.sendMessage("§b§lSKIT §fUtilize: /skit <criar> [nome]");
            return true;
        }
        final String prekitname = args[1];
        this.kit.put(prekitname, p.getInventory().getContents());
        this.armadura.put(prekitname, p.getInventory().getArmorContents());
        p.sendMessage("§b§lSKIT §fO §3§lKIT §7(§c" + args[1] + "§7) foi §e§lCRIADO §fcom §a§lSUCESSO§7!");
        return true;
    }
}
