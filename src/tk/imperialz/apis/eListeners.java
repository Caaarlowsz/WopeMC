package tk.imperialz.apis;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import tk.imperialz.comandos.Build;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerPickupItemEvent;
import tk.imperialz.Main;
import org.bukkit.event.Listener;

public class eListeners implements Listener
{
    public Main m;
    
    public eListeners(final Main m) {
        this.m = m;
    }
    
    @EventHandler
    public void onPickup(final PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        if (KitAPI.getkit(p) == "1v1" || KitAPI.getkit(p) == "Nenhum") {
            e.setCancelled(true);
            return;
        }
        if (e.getItem().getItemStack().getType() == Material.MUSHROOM_SOUP || e.getItem().getItemStack().getType() == Material.BROWN_MUSHROOM || e.getItem().getItemStack().getType() == Material.RED_MUSHROOM) {
            return;
        }
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onClick(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
    }
    
    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        if (!this.m.perm.isMod(p) || Build.Build.get(p.getName()) == Build.BuildStats.OFF) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(final PlayerLoginEvent event) {
        final Player p = event.getPlayer();
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            if (this.m.perm.isExtreme(p)) {
                event.allow();
            }
            else {
                event.setKickMessage("§4§lWHITELIST §fO servidor est\u00e1 cheio, para entrar adquira §6VIP§f em §ewww.wopemc.com.br");
            }
        }
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        if (!this.m.perm.isMod(p) && e.getBlock().getType() != Material.COBBLESTONE) {
            e.setCancelled(true);
        }
        if (Build.Build.get(p.getName()) == Build.BuildStats.OFF) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDrop(final PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP && e.getItemDrop().getItemStack().getType() != Material.BOWL && e.getItemDrop().getItemStack().getType() != Material.ENDER_PEARL && e.getItemDrop().getItemStack().getType() != Material.RED_MUSHROOM && e.getItemDrop().getItemStack().getType() != Material.BROWN_MUSHROOM && e.getItemDrop().getItemStack().getType() != Material.IRON_HELMET && e.getItemDrop().getItemStack().getType() != Material.IRON_CHESTPLATE && e.getItemDrop().getItemStack().getType() != Material.IRON_LEGGINGS && e.getItemDrop().getItemStack().getType() != Material.IRON_BOOTS && e.getItemDrop().getItemStack().getType() != Material.GLASS_BOTTLE) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            final Player d = (Player)e.getDamager();
            if (d.getItemInHand().getType() == Material.DIAMOND_SWORD || d.getItemInHand().getType() == Material.STONE_SWORD || d.getItemInHand().getType() == Material.WOOD_SWORD || d.getItemInHand().getType() == Material.STONE_SWORD || d.getItemInHand().getType() == Material.IRON_SWORD || d.getItemInHand().getType() == Material.GOLD_SWORD || d.getItemInHand().getType() == Material.FISHING_ROD || d.getItemInHand().getType() == Material.DIAMOND_AXE || d.getItemInHand().getType() == Material.GOLD_AXE || d.getItemInHand().getType() == Material.STONE_AXE || d.getItemInHand().getType() == Material.WOOD_AXE || d.getItemInHand().getType() == Material.IRON_AXE) {
                d.getItemInHand().setDurability((short)0);
                d.updateInventory();
            }
        }
    }
}
