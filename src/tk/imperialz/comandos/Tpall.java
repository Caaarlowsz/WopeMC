package tk.imperialz.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import tk.imperialz.Main;

public class Tpall implements CommandExecutor {
	public Main m;

	public Tpall(final Main m) {
		this.m = m;
	}

	public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
		final Player p = (Player) Sender;
		if (!this.m.perm.isOwner(p)) {
			p.sendMessage(Main.perma);
			return true;
		}
		Player[] arrayOfPlayer;
		for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
			final Player All = arrayOfPlayer[i];
			All.teleport((Entity) p);
			All.sendMessage("§a§lTPALL §fTodos os jogadores foram teleportados para " + p.getDisplayName());
		}
		return false;
	}
}
