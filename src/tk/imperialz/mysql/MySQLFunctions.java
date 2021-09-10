package tk.imperialz.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tk.imperialz.Main;
import tk.imperialz.gamer.DataManager;
import tk.imperialz.gamer.PlayerData;

public class MySQLFunctions {
	public static void CriarTabela() {
		try {
			final PreparedStatement ps = MySQL.getStatement(
					"CREATE TABLE IF NOT EXISTS KitPvP (UUID VARCHAR(100), NICK VARCHAR(100), Streak INT(100), Kills INT(100), Deaths INT(100))");
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void Registro(final Player p) {
		try {
			final PreparedStatement ps = MySQL
					.getStatement("INSERT INTO KitPvP (UUID, NICK, Streak, Kills, Deaths) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, p.getUniqueId().toString());
			ps.setString(2, p.getName());
			ps.setInt(3, 50);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void savePlayer(final Player p) {
		Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
			try {
				PlayerData data = DataManager.getPlayerData(p);
				data.update();
			} catch (Exception ex) {
			}
		});
	}

	public static boolean VerificarRegistro(final UUID uuid) {
		try {
			final PreparedStatement ps = MySQL.getStatement("SELECT * FROM KitPvP WHERE UUID= ?");
			ps.setString(1, uuid.toString());
			final ResultSet rs = ps.executeQuery();
			final boolean user = rs.next();
			rs.close();
			ps.close();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
