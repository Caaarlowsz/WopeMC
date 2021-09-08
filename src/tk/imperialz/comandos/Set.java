package tk.imperialz.comandos;

import tk.imperialz.warps.WarpsAPI;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tk.imperialz.Main;
import org.bukkit.command.CommandExecutor;

public class Set implements CommandExecutor
{
    public Main m;
    
    public Set(final Main m) {
        this.m = m;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("set")) {
            if (!this.m.perm.isGerente(p)) {
                p.sendMessage(Main.perma);
                return true;
            }
            if (args.length == 0) {
                p.sendMessage("§5§lSET §fUtilize: /set <warp>");
                return true;
            }
            if (args[0].equalsIgnoreCase("spawn")) {
                WarpsAPI.Set(p, "Spawn");
                p.sendMessage("§6§lSET§f O local [§eSpawn] foi setado com sucesso.");
            }
            else if (args[0].equalsIgnoreCase("challenge")) {
                WarpsAPI.Set(p, "Challenge");
                p.sendMessage("§6§lSET§f O local [§eChallenge] foi setado com sucesso.");
            }
            else if (args[0].equalsIgnoreCase("fps")) {
                WarpsAPI.Set(p, "Fps");
                p.sendMessage("§6§lSET§f O local [§eFps] foi setado com sucesso.");
            }
            else if (args[0].equalsIgnoreCase("evento")) {
                WarpsAPI.Set(p, "Evento");
                p.sendMessage("§6§lSET§f O local [§eEvento] foi setado com sucesso.");
            }
            else {
                if (args[0].equalsIgnoreCase("1v1")) {
                    WarpsAPI.Set(p, "1v1");
                    p.sendMessage("§6§lSET§f O local [§e1v1] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("1v1loc1")) {
                    WarpsAPI.Set(p, "1v1loc1");
                    p.sendMessage("§6§lSET§f O local [§eLOC1] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("1v1loc2")) {
                    WarpsAPI.Set(p, "1v1loc2");
                    p.sendMessage("§6§lSET§f O local [§eLOC2] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("knockback")) {
                    WarpsAPI.Set(p, "Knockback");
                    p.sendMessage("§6§lSET§f O local [§eKnockback] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("textura")) {
                    WarpsAPI.Set(p, "Textura");
                    p.sendMessage("§6§lSET§f O local [§eTextura] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("ss")) {
                    WarpsAPI.Set(p, "Screenshare");
                    p.sendMessage("§6§lSET§f O local [§eScreenshare] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("1")) {
                    WarpsAPI.Set(p, "1");
                    p.sendMessage("§6§lSET§f O local [§e1] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("2")) {
                    WarpsAPI.Set(p, "2");
                    p.sendMessage("§6§lSET§f O local [§e2] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("3")) {
                    WarpsAPI.Set(p, "3");
                    p.sendMessage("§6§lSET§f O local [§e3] foi setado com sucesso.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("potion")) {
                    WarpsAPI.Set(p, "Potion");
                    p.sendMessage("§6§lSET§f O local [§ePotion] foi setado com sucesso.");
                    return true;
                }
            }
        }
        return false;
    }
}
