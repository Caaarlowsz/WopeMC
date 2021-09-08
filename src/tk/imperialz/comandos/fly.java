package tk.imperialz.comandos;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class fly implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("§cComando apenas para jogadores!");
            return true;
        }
        final Player p = (Player)sender;
        if (!p.hasPermission("voar.usar")) {
            p.sendMessage("§cVoce nao tem permissao para usar esse comando.");
            return true;
        }
        if (p.getAllowFlight()) {
            p.setAllowFlight(false);
            p.setFlying(false);
            p.sendMessage("§cVoar Desativado!");
        }
        else {
            p.setAllowFlight(true);
            p.sendMessage("§aVoar Ativado!");
        }
        return true;
    }
}
