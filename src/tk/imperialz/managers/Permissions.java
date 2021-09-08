package tk.imperialz.managers;

import org.bukkit.command.CommandSender;
import com.extremepvp.permissions.enums.Group;
import org.bukkit.entity.Player;
import tk.imperialz.Main;

public class Permissions
{
    public Main m;
    
    public Permissions(final Main m) {
        this.m = m;
    }
    
    public boolean isLow(final Player p) {
        return this.isPro(p) || p.hasPermission("heavenmc.low") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.LOW);
    }
    
    public boolean isPro(final Player p) {
        return this.isExtreme(p) || p.hasPermission("heavenmc.pro") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.PRO);
    }
    
    public boolean isExtreme(final Player p) {
        return this.isLegend(p) || p.hasPermission("heavenmc.extreme") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.EXTREME);
    }
    
    public boolean isLegend(final Player p) {
        return this.isYouTuber(p) || p.hasPermission("heavenmc.legend") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.LEGEND);
    }
    
    public boolean isYouTuber(final Player p) {
        return this.isTrial(p) || p.hasPermission("heavenmc.youtuber") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.YOUTUBER);
    }
    
    public boolean isBuilder(final Player p) {
        return this.isYouTuber(p) || p.hasPermission("heavenmc.builder") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.BUILDER);
    }
    
    public boolean isDesigner(final Player p) {
        return this.isBuilder(p) || p.hasPermission("heavenmc.designer") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.DESIGNER);
    }
    
    public boolean isTrial(final Player p) {
        return this.isYoutuberPlus(p) || p.hasPermission("heavenmc.trial") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.TRIAL);
    }
    
    public boolean isYoutuberPlus(final Player p) {
        return this.isMod(p) || p.hasPermission("heavenmc.youtuberplus") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.YOUTUBERPLUS);
    }
    
    public boolean isMod(final Player p) {
        return this.isModPlus(p) || p.hasPermission("heavenmc.mod") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.MOD);
    }
    
    public boolean isModPlus(final Player p) {
        return this.isGerente(p) || p.hasPermission("heavenmc.modplus") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.MODPLUS);
    }
    
    public boolean isGerente(final Player p) {
        return this.isAdmin(p) || p.hasPermission("heavenmc.gerente") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.GERENTE);
    }
    
    public boolean isAdmin(final Player p) {
        return this.isOwner(p) || p.hasPermission("heavenmc.admin") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.ADMIN);
    }
    
    public boolean isOwner(final Player p) {
        return p.hasPermission("heavenmc.dono") || com.extremepvp.Main.getInstance().getPermissionManager().isGroup(p, Group.DONO) || p.isOp();
    }
    
    public boolean isTrial(final CommandSender sender) {
        return this.isMod(sender) || sender.hasPermission("heavenmc.trial");
    }
    
    public boolean isMod(final CommandSender sender) {
        return sender.hasPermission("heavenmc.mod");
    }
    
    public boolean isGerente(final CommandSender sender) {
        return sender.hasPermission("heavenmc.gerente");
    }
    
    public boolean isAdmin(final CommandSender sender) {
        return sender.hasPermission("heavenmc.admin");
    }
}
