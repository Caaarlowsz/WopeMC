package tk.imperialz.gamer;

import java.sql.SQLException;
import tk.imperialz.mysql.MySQL;
import java.util.Collection;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class DataManager
{
    private static final HashMap<UUID, PlayerData> datas;
    
    static {
        datas = new HashMap<UUID, PlayerData>();
    }
    
    public static PlayerData addPlayerData(final PlayerData playerData) {
        DataManager.datas.put(playerData.getUUID(), playerData);
        return playerData;
    }
    
    public static void removePlayerData(final UUID uniqueId) {
        DataManager.datas.remove(uniqueId);
    }
    
    public static PlayerData getPlayerData(final UUID uniqueId) {
        return DataManager.datas.get(uniqueId);
    }
    
    public static PlayerData getPlayerData(final Player player) {
        return DataManager.datas.get(player.getUniqueId());
    }
    
    public static Collection<PlayerData> getPlayerDatas() {
        return DataManager.datas.values();
    }
    
    public static void createPlayer(final UUID uuid, final String nick) {
        try {
            MySQL.con.createStatement().executeUpdate("INSERT INTO `KitPvP` (`uuid`, `kills`, `deaths`, `nick`) VALUES ('" + uuid + "', '0', '0', '" + nick + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateData(final PlayerData playerData) {
        try {
            MySQL.con.createStatement().executeUpdate("UPDATE `KitPvP` SET `Kills`='" + playerData.getKills() + "', `NICK`='" + playerData.getNick() + "', `Deaths`='" + playerData.getDeaths() + "' WHERE `uuid`='" + playerData.getUUID() + "';");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
