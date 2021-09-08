package tk.imperialz.apis;

import org.bukkit.entity.Player;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class TopKsAPI
{
    public static File TopKss;
    public static FileConfiguration TopKs;
    
    public static void createFile1() {
        TopKsAPI.TopKss = new File("plugins/PVP/TopKs.yml");
        TopKsAPI.TopKs = (FileConfiguration)YamlConfiguration.loadConfiguration(TopKsAPI.TopKss);
        saveTopKs();
    }
    
    public static void saveTopKs() {
        try {
            TopKsAPI.TopKs.save(TopKsAPI.TopKss);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void createTopKsAcc(final Player p) {
        if (!TopKsAPI.TopKs.contains(p.getName())) {
            TopKsAPI.TopKs.set(p.getName(), (Object)0);
        }
    }
    
    public static int getStreak(final Player p) {
        return TopKsAPI.TopKs.getInt(p.getName());
    }
    
    public static void addTopKs(final Player p, final int Qntd) {
        TopKsAPI.TopKs.set(p.getName(), (Object)(getStreak(p) + Qntd));
    }
    
    public static void removeTopKs(final Player p, final int Qntd) {
        TopKsAPI.TopKs.set(p.getName(), (Object)(getStreak(p) - Qntd));
    }
    
    public static void removeTopKs(final String name, final int d) {
    }
}
