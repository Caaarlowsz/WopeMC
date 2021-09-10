package tk.imperialz.automatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import tk.imperialz.Main;

public class CommandManager {
	private Main m;

	public CommandManager(final Main m) {
		this.loadCommands(this.m = m, "tk.imperialz.comandos");
	}

	private void loadCommands(final JavaPlugin plugin, final String packageName) {
		try {
			for (final Class<?> commandClass : ClassGetter.getClassesForPackage(this.m, packageName)) {
				if (CommandExecutor.class.isAssignableFrom(commandClass)) {
					try {
						CommandExecutor commandListener = null;
						try {
							final Constructor<?> con = commandClass.getConstructor(Main.class);
							commandListener = (CommandExecutor) con.newInstance(plugin);
						} catch (Exception ex) {
							commandListener = (CommandExecutor) commandClass.newInstance();
						}
						this.loadCommand(plugin, commandListener);
						System.out.println("§cComando Carregado: " + commandClass.getSimpleName() + " §cCom Sucesso");
					} catch (Exception e2) {
						System.out.print("Erro ao carregar o comando " + commandClass.getSimpleName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadCommand(final JavaPlugin owningPlugin, final CommandExecutor exc) throws Exception {
		this.registerCommand(exc.getClass().getSimpleName(), exc);
	}

	private void registerCommand(final String name, final CommandExecutor exc) throws Exception {
		PluginCommand command = Bukkit.getServer().getPluginCommand(name.toLowerCase());
		if (command == null) {
			final Constructor<?> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			constructor.setAccessible(true);
			command = (PluginCommand) constructor.newInstance(name, this.m);
		}
		command.setExecutor(exc);
		try {
			final Field field = exc.getClass().getDeclaredField("aliases");
			if (field.get(exc) instanceof String[]) {
				final List<String> list = Arrays.asList((String[]) field.get(exc));
				command.setAliases(list);
			}
		} catch (Exception ex) {
		}
		if (command.getAliases() != null) {
			for (final String alias : command.getAliases()) {
				this.unregisterCommand(alias);
			}
		}
		try {
			final Field field = exc.getClass().getDeclaredField("description");
			if (field != null && field.get(exc) instanceof String) {
				command.setDescription(ChatColor.translateAlternateColorCodes('&', (String) field.get(exc)));
			}
		} catch (Exception ex2) {
		}
		this.m.rm.getCommandMap().register(name, (Command) command);
	}

	@SuppressWarnings("unchecked")
	private void unregisterCommand(final String name) {
		try {
			final Field known = SimpleCommandMap.class.getDeclaredField("knownCommands");
			final Field alias = SimpleCommandMap.class.getDeclaredField("aliases");
			known.setAccessible(true);
			alias.setAccessible(true);
			final Map<String, Command> knownCommands = (Map<String, Command>) known.get(this.m.rm.getCommandMap());
			final Set<String> aliases = (Set<String>) alias.get(this.m.rm.getCommandMap());
			knownCommands.remove(name.toLowerCase());
			aliases.remove(name.toLowerCase());
		} catch (Exception ex) {
		}
	}
}
