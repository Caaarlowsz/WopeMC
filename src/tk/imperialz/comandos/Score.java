package tk.imperialz.comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Score implements CommandExecutor, Listener {
	public static ArrayList<String> taoff;

	static {
		Score.taoff = new ArrayList<String>();
	}

	public static Scoreboard getScoreBoard() {
		final ScoreboardManager manager = Bukkit.getScoreboardManager();
		final Scoreboard board = manager.getNewScoreboard();
		return board;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		final Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§e§lSCOREBOARD§f <on> <off>");
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("off")) {
				p.sendMessage("§e§lSCOREBOARD §fVoc\u00ea §c§lDESATIVOU§f sua scoreboard.");
				Score.taoff.add(p.getName());
				getScoreBoard().clearSlot(DisplaySlot.SIDEBAR);
				p.setScoreboard(getScoreBoard());
			} else if (args[0].equalsIgnoreCase("on")) {
				p.sendMessage("§e§lSCOREBOARD §fVoc\u00ea §a§lATIVOU§f sua scoreboard.");
				Score.taoff.remove(p.getName());
			}
			return true;
		}
		return false;
	}
}
