package tk.imperialz.comandos;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.imperialz.Main;

public class Limpar implements CommandExecutor {
	public static List<String> registered;
	public Main m;

	public Limpar(final Main m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
		final Player p = (Player) Sender;
		if (Cmd.getName().equalsIgnoreCase("limpar")) {
			if (!this.m.perm.isTrial(p)) {
				p.sendMessage(Main.perma);
				return true;
			}
			Bukkit.broadcastMessage("§fExecutando limpeza do servidor. O servidor pode travar, mas voltar\u00e1!");
			final Runtime r2 = Runtime.getRuntime();
			final long Lused2 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
			System.gc();
			final long Lused3 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
			Player[] onlinePlayers;
			for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
				final Player s = onlinePlayers[i];
				if (!this.m.perm.isTrial(s)) {
					s.sendMessage("§aForam removidos §e" + Long.toString(Lused2 - Lused3) + "M §aRAM do servidor");
				}
			}
			Bukkit.broadcastMessage("§b§lLIMPEZA §fA limpeza foi concluida.");
		}
		return false;
	}
}
