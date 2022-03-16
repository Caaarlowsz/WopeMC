package tk.imperialz.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;

public class Dano implements CommandExecutor {
	public WopePvP m;

	public Dano(final WopePvP m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
		if (Cmd.getName().equalsIgnoreCase("dano")) {
			Player[] onlinePlayers;
			for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
				final Player todos = onlinePlayers[i];
				final Player p = (Player) Sender;
				if (!this.m.perm.isMod(p)) {
					p.sendMessage(WopePvP.perma);
					return true;
				}
				if (Args.length != 1) {
					p.sendMessage("�4�lDANO �fUtilize: /dano <on> <off>");
					return true;
				}
				if (Args[0].equalsIgnoreCase("off")) {
					Bukkit.getServer()
							.broadcastMessage("�4�lDANO �fO dano do servidor foi desativado por " + p.getDisplayName());
					todos.getWorld().setPVP(false);
					return true;
				}
				if (Args[0].equalsIgnoreCase("on")) {
					Bukkit.getServer()
							.broadcastMessage("�4�lDANO �fO dano do servidor foi ativado por " + p.getDisplayName());
					todos.getWorld().setPVP(true);
					return true;
				}
			}
		}
		return false;
	}
}
