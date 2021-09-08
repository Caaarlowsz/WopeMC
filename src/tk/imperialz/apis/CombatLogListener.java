package tk.imperialz.apis;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class CombatLogListener implements Listener
{
    private CombatLogManager manager;
    
    public CombatLogListener(final CombatLogManager manager) {
        this.manager = manager;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(final EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        final Player damager = (Player)event.getDamager();
        final Player damaged = (Player)event.getEntity();
        if (KitAPI.getkit(damager) == "Nenhum") {
            return;
        }
        if (KitAPI.getkit(damaged) == "Nenhum") {
            return;
        }
        this.manager.newCombatLog(damaged.getUniqueId(), damager.getUniqueId());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(final PlayerDeathEvent event) {
        final Player p = event.getEntity();
        this.manager.removeCombatLog(p.getUniqueId());
    }
    
    @EventHandler
    public void onCmd(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final CombatLog log = this.manager.getCombatLog(p.getUniqueId());
        if (log == null) {
            return;
        }
        if (System.currentTimeMillis() < log.getTime()) {
            final Player combatLogger = Bukkit.getPlayer(log.getCombatLogged());
            if (combatLogger != null && combatLogger.isOnline()) {
                e.setCancelled(true);
                p.sendMessage("§4§lCOMBATLOG §fN\u00e3o \u00e9 poss\u00edvel executar comandos em combate!");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(final PlayerQuitEvent event) {
        final Player p = event.getPlayer();
        final CombatLog log = this.manager.getCombatLog(p.getUniqueId());
        if (log == null) {
            return;
        }
        if (System.currentTimeMillis() < log.getTime()) {
            final Player combatLogger = Bukkit.getPlayer(log.getCombatLogged());
            if (combatLogger != null && combatLogger.isOnline()) {
                p.damage(10000.0, (Entity)combatLogger);
            }
        }
        this.manager.removeCombatLog(p.getUniqueId());
    }
    
    @EventHandler
    public void onVoidDamage(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (event.getCause() != EntityDamageEvent.DamageCause.VOID) {
            return;
        }
        final Player p = (Player)event.getEntity();
        final CombatLog log = this.manager.getCombatLog(p.getUniqueId());
        if (log == null) {
            return;
        }
        if (System.currentTimeMillis() < log.getTime()) {
            final Player combatLogger = Bukkit.getPlayer(log.getCombatLogged());
            if (combatLogger != null && combatLogger.isOnline()) {
                p.damage(10000.0, (Entity)combatLogger);
            }
        }
        this.manager.removeCombatLog(p.getUniqueId());
    }
}
