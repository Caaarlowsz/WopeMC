package tk.imperialz.automatic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.caaarlowsz.wopemc.kitpvp.WopePvP;

public class ListenerManager {
	public static void loadListener() {
		System.out.println("Iniciando o carregamento dos eventos..");
		for (final Class<?> Classes : ClassGetter.getClassesForPackage(WopePvP.getPlugin(WopePvP.class),
				"tk.imperialz.eventos")) {
			try {
				if (!Listener.class.isAssignableFrom(Classes)) {
					continue;
				}
				final Listener listener = (Listener) Classes.newInstance();
				Bukkit.getPluginManager().registerEvents(listener, (Plugin) WopePvP.getPlugin(WopePvP.class));
				Bukkit.getConsoleSender()
						.sendMessage(String.valueOf(String.valueOf(listener.getClass().getSimpleName()))
								+ "(Listener) carregado com sucesso!");
			} catch (Exception e) {
				System.out.println("Aconteceu um erro ao carregar a listener: " + e.getMessage());
			}
		}
	}

	public static void loadKits() {
		System.out.println("Iniciando o carregamento de kits..");
		for (final Class<?> Classes : ClassGetter.getClassesForPackage(JavaPlugin.getPlugin(WopePvP.class),
				"tk.imperialz.habilidades")) {
			try {
				if (!Listener.class.isAssignableFrom(Classes)) {
					continue;
				}
				final Listener listener = (Listener) Classes.newInstance();
				Bukkit.getPluginManager().registerEvents(listener, (Plugin) JavaPlugin.getPlugin(WopePvP.class));
				Bukkit.getConsoleSender()
						.sendMessage("�6�l* " + listener.getClass().getSimpleName() + " Kit Carregado");
			} catch (Exception e) {
				System.out.println("Aconteceu um erro ao carregar os Kits: " + e.getMessage());
			}
		}
	}
}
