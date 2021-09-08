package tk.imperialz.comandos;

import org.bukkit.Sound;
import tk.imperialz.apis.KitAPI;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Spawn implements CommandExecutor
{
    public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
        final Player p = (Player)Sender;
        if (Cmd.getName().equalsIgnoreCase("spawn")) {
            if (!p.isOnGround()) {
                p.sendMessage("§cVoc\u00ea precisa estar no ch\u00e3o para se teleportar.");
                return true;
            }
            KitAPI.darItens(p);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
            p.sendMessage("§aVoc\u00ea foi teleportado ao Spawn.");
        }
        return false;
    }
}
