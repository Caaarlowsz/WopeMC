package tk.imperialz.comandos;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;

public class Build implements Listener, CommandExecutor {
	public WopePvP m;
	public static HashMap<String, BuildStats> Build;

	static {
		tk.imperialz.comandos.Build.Build = new HashMap<String, BuildStats>();
	}

	public Build(final WopePvP m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�bPrecisa ser um Player para usar esse comando");
			return true;
		}
		final Player p = (Player) sender;
		if (!this.m.perm.isMod(p)) {
			sender.sendMessage(WopePvP.perma);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("�2�lBUILD �f/build <on> <off>");
		} else if (args[0].equalsIgnoreCase("on")) {
			if (tk.imperialz.comandos.Build.Build.get(p.getName()) == BuildStats.ON) {
				p.sendMessage("�2�lBUILD �fSeu modo de constru\u00e7\u00e3o j\u00e1 est\u00e1 ativado.");
				return true;
			}
			p.sendMessage("�2�lBUILD �fSeu modo de constru\u00e7\u00e3o foi ativado.");
			tk.imperialz.comandos.Build.Build.put(p.getName(), BuildStats.ON);
			return true;
		} else if (args[0].equalsIgnoreCase("off")) {
			if (tk.imperialz.comandos.Build.Build.get(p.getName()) == BuildStats.OFF) {
				p.sendMessage("�2�lBUILD �fSeu modo de constru\u00e7\u00e3o j\u00e1 est\u00e1 desativado.");
				return true;
			}
			p.sendMessage("�2�lBUILD �fSeu modo de constru\u00e7\u00e3o foi desativado.");
			tk.imperialz.comandos.Build.Build.put(p.getName(), BuildStats.OFF);
			return true;
		}
		return false;
	}

	public enum BuildStats {
		ON("ON", 0, "ON", 0), OFF("OFF", 1, "OFF", 1);

		private BuildStats(final String s, final int n, final String name, final int ordinal) {
		}

		static boolean contains(final Player all) {
			return false;
		}

		static void remove(final Player all) {
		}
	}
}
