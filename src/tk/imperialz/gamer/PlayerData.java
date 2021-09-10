package tk.imperialz.gamer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.entity.Player;

import tk.imperialz.mysql.MySQL;

public class PlayerData {
	private UUID uuid;
	private boolean isLoaded;
	private Integer kills;
	private Integer deaths;
	private Integer streak;
	private Player player;
	private String nick;

	public PlayerData(final UUID uuid, final String nick) {
		this.uuid = uuid;
		this.nick = nick;
		this.kills = 0;
		this.deaths = 0;
		this.streak = 0;
	}

	public void load() {
		if (this.isLoaded) {
			return;
		}
		try {
			final Statement statment = MySQL.con.createStatement();
			final ResultSet result = statment
					.executeQuery("SELECT * FROM `KitPvP` WHERE `uuid` = '" + this.uuid.toString() + "';");
			if (result.next()) {
				this.kills = result.getInt("kills");
				this.deaths = result.getInt("deaths");
				this.streak = result.getInt("streak");
			}
			result.close();
			statment.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public Integer getKills() {
		return this.kills;
	}

	public Integer getDeaths() {
		return this.deaths;
	}

	public Integer getStreak() {
		return this.streak;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public void setUUID(final UUID uuid) {
		this.uuid = uuid;
	}

	public void setKills(final Integer kills) {
		this.kills = kills;
	}

	public void setDeaths(final Integer deaths) {
		this.deaths = deaths;
	}

	public void setStreak(final Integer streak) {
		this.streak = streak;
	}

	public void update() {
		DataManager.updateData(this);
	}

	public void create() {
		DataManager.createPlayer(this.uuid, this.nick);
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "PlayerData[UUID=" + this.uuid + ", NICK=" + this.nick + ", KILLS= " + this.kills + ", DEATHS="
				+ this.deaths + ", STREAK=" + this.streak + "]";
	}
}
