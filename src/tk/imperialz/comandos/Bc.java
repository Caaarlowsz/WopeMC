package tk.imperialz.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Bc implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel,
			final String[] args) {
		if (!sender.hasPermission("kitpvp.bc")) {
			sender.sendMessage(
					"§e§lPERMISSAO §fVoc\u00ea n\u00e3o possui §4§lPERMISSAO §fpara executar este §3§lCOMANDO.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§6§lBROADCAST §fUtilize: /bc <mensagem>");
			return true;
		}
		if (args.length >= 1) {
			String message = "";
			for (int x = 0; x < args.length; ++x) {
				message = String.valueOf(message) + args[x] + " ";
			}
			Bukkit.getServer().broadcastMessage("              ");
			Bukkit.getServer().broadcastMessage("§c§lAVISO §7> " + message);
			Bukkit.getServer().broadcastMessage("              ");
		} else {
			sender.sendMessage("§6§lBROADCAST §fUtilize: /bc <mensagem>");
		}
		return false;
	}
}
