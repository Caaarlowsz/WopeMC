package tk.imperialz.automatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.libs.joptsimple.OptionSet;
import org.bukkit.entity.Player;

public class ReflectionManager {
	private SimpleCommandMap commandMap;
	private String currentVersion;
	private Object propertyManager;

	public ReflectionManager() {
		try {
			this.commandMap = (SimpleCommandMap) Bukkit.getServer().getClass()
					.getDeclaredMethod("getCommandMap", (Class<?>[]) new Class[0])
					.invoke(Bukkit.getServer(), new Object[0]);
			final Object obj = Bukkit.getServer().getClass().getDeclaredMethod("getServer", (Class<?>[]) new Class[0])
					.invoke(Bukkit.getServer(), new Object[0]);
			this.propertyManager = obj.getClass().getDeclaredMethod("getPropertyManager", (Class<?>[]) new Class[0])
					.invoke(obj, new Object[0]);
			this.currentVersion = this.propertyManager.getClass().getPackage().getName();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public SimpleCommandMap getCommandMap() {
		return this.commandMap;
	}

	public Object getPropertiesConfig(final String name, final Object obj) {
		try {
			final Properties properties = (Properties) this.propertyManager.getClass().getDeclaredField("properties")
					.get(this.propertyManager);
			if (!properties.containsKey(name)) {
				properties.setProperty(name, (String) obj);
				this.savePropertiesConfig();
			}
			final Field opt = this.propertyManager.getClass().getDeclaredField("options");
			opt.setAccessible(true);
			final OptionSet options = (OptionSet) opt.get(this.propertyManager);
			if (options != null && options.has(name) && !name.equals("online-mode")) {
				return options.valueOf(name);
			}
			if (obj instanceof String) {
				return properties.getProperty(name, (String) obj);
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		}
	}

	public void removeArrows(final Player player) {
		try {
			final Method handle = player.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]);
			String methodName = "abcdefg";
			if (this.currentVersion.contains("v1_6_R2")) {
				methodName = "m";
			}
			handle.invoke(player, new Object[0]).getClass().getMethod(methodName, Integer.TYPE)
					.invoke(handle.invoke(player, new Object[0]), 0);
		} catch (Exception ex) {
		}
	}

	public void savePropertiesConfig() {
		try {
			this.propertyManager.getClass().getMethod("savePropertiesFile", (Class<?>[]) new Class[0])
					.invoke(this.propertyManager, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendChunk(final Player p, final int x, final int z) {
		try {
			final Object obj = p.getClass().getDeclaredMethod("getHandle", (Class<?>[]) new Class[0]).invoke(p,
					new Object[0]);
			final List list = (List) obj.getClass().getField("chunkCoordIntPairQueue").get(obj);
			final Constructor<?> con = Class
					.forName(String.valueOf(String.valueOf(this.currentVersion)) + ".ChunkCoordIntPair")
					.getConstructor(Integer.TYPE, Integer.TYPE);
			list.add(con.newInstance(x, z));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Object setPropertiesConfig(final String name, final Object obj) {
		try {
			return this.propertyManager.getClass().getMethod("a", String.class, Object.class)
					.invoke(this.propertyManager, name, obj);
		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		}
	}

	public void setWidthHeight(final Player p, final float height, final float width, final float length) {
		try {
			final Method handle = p.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]);
			final Class<?> c = Class.forName(String.valueOf(String.valueOf(this.currentVersion)) + ".Entity");
			final Field field1 = c.getDeclaredField("height");
			final Field field2 = c.getDeclaredField("width");
			final Field field3 = c.getDeclaredField("length");
			field1.setFloat(handle.invoke(p, new Object[0]), height);
			field2.setFloat(handle.invoke(p, new Object[0]), width);
			field3.setFloat(handle.invoke(p, new Object[0]), length);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
