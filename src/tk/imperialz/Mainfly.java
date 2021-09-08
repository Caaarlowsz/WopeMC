package tk.imperialz;

import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Mainfly extends JavaPlugin
{
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§2EsVoar Ativado");
        this.getCommand("fly").setExecutor((CommandExecutor)new Mainfly());
    }
    
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cEsVoar desativado");
    }
}
