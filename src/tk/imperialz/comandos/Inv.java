package tk.imperialz.comandos;

import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;

public class Inv implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVoc\u00ea n\u00e3o \u00e9 um jogador.");
            return false;
        }
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("inv")) {
            if (p.hasPermission("kitpvp.inv")) {
                if (args.length == 0) {
                    p.sendMessage("§6§lINVENTARIO §7Utilize: /inv <jogador>");
                }
                else if (args.length == 1) {
                    final Player target = p.getServer().getPlayer(args[0]);
                    final String nameoff = args[0];
                    if (target != null) {
                        p.sendMessage("§6§lINVENTARIO §fVoc\u00ea §a§labriu §fo invent\u00e1rio do jogador §7(§e" + target.getName() + "§7)");
                        p.openInventory((Inventory)target.getInventory());
                    }
                    else {
                        p.sendMessage("§f§lOFFLINE §fO jogador §7(§e" + nameoff + "§7) §fest\u00e1 offline.");
                    }
                }
                else {
                    p.sendMessage("§6§lINVENTARIO §7Utilize: /inv <jogador>");
                }
            }
            else {
                p.sendMessage("§e§lPERMISSAO §fVoc\u00ea n\u00e3o possui §4§lPERMISSAO §fpara executar este §3§lCOMANDO.");
            }
        }
        return false;
    }
}
