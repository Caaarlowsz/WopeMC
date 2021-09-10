package tk.imperialz.jumps;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class EndPortal implements Listener {
	public static ArrayList<String> jump;

	static {
		EndPortal.jump = new ArrayList<String>();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void aoMove(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.getMaterial(120)) {
			EndPortal.jump.remove(p.getName());
			final Location loc = e.getTo().getBlock().getLocation();
			final Vector sponge = p.getLocation().getDirection().multiply(0).setY(3.5);
			p.setVelocity(sponge);
			p.playSound(loc, Sound.HORSE_JUMP, 6.0f, 1.0f);
			p.playEffect(loc, Effect.ENDER_SIGNAL, (Object) null);
			p.playEffect(loc, Effect.CLICK1, (Object) null);
			p.playEffect(loc, Effect.BLAZE_SHOOT, (Object) null);
			EndPortal.jump.add(p.getName());
		}
	}

	@EventHandler
	public void aoQueda(final EntityDamageEvent e) {
		final Player p = (Player) e.getEntity();
		if (e.getEntity() instanceof Player && e.getCause().equals((Object) EntityDamageEvent.DamageCause.FALL)
				&& EndPortal.jump.contains(p.getName())) {
			e.setCancelled(true);
			EndPortal.jump.remove(p.getName());
		}
	}
}
