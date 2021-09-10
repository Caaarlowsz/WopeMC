package tk.imperialz.warps;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WarpsManager implements Listener {
	public static ArrayList<String> segundos;

	static {
		WarpsManager.segundos = new ArrayList<String>();
	}

	public static void Set(final Player p, final String Warp) {
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".X", (Object) p.getLocation().getX());
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".Y", (Object) p.getLocation().getY());
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".Z", (Object) p.getLocation().getZ());
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".Pitch", (Object) p.getLocation().getPitch());
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".Yaw", (Object) p.getLocation().getYaw());
		WarpsConfig.getConfig().config().set("Warps." + Warp + ".World", (Object) p.getLocation().getWorld().getName());
		WarpsConfig.getConfig().saveAllWarps();
	}

	public static Location getWarpLocation() {
		final World world = Bukkit.getWorld(WarpsConfig.getConfig().config().getString("Warps..World"));
		final double X = WarpsConfig.getConfig().config().getInt("Warps..X");
		final double Y = WarpsConfig.getConfig().config().getInt("Warps..Y");
		final double Z = WarpsConfig.getConfig().config().getInt("Warps..Z");
		final float Yaw = (float) WarpsConfig.getConfig().config().getInt("Warps..Yaw");
		final float Pitch = (float) WarpsConfig.getConfig().config().getInt("Warps..Pitch");
		final Location location = new Location(world, X, Y, Z, Yaw, Pitch);
		return location;
	}

	public static void Ir(final Player p, final String Warp) {
		if (WarpsConfig.getConfig().config().contains("Warps." + Warp)) {
			final double x = WarpsConfig.getConfig().config().getDouble("Warps." + Warp + ".X");
			final double y = WarpsConfig.getConfig().config().getDouble("Warps." + Warp + ".Y");
			final double z = WarpsConfig.getConfig().config().getDouble("Warps." + Warp + ".Z");
			final float Pitch = (float) WarpsConfig.getConfig().config().getDouble("Warps." + Warp + ".Pitch");
			final float Yaw = (float) WarpsConfig.getConfig().config().getDouble("Warps." + Warp + ".Yaw");
			final World world = Bukkit.getWorld(WarpsConfig.getConfig().config().getString("Warps." + Warp + ".World"));
			final Location loc = new Location(world, x, y, z, Yaw, Pitch);
			p.teleport(loc);
		} else {
			p.sendMessage(
					"§6§lWARP §fEsta §e§lWARP §fainda n\u00e3o foi §a§lSETADA§f! Avise um §d§lSTAFFER §fpara §a§lSETA-LA§f!");
		}
	}
}
