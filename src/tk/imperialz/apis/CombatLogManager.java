package tk.imperialz.apis;

import java.util.UUID;
import java.util.HashMap;

public class CombatLogManager
{
    private HashMap<UUID, CombatLog> combats;
    
    public CombatLogManager() {
        this.combats = new HashMap<UUID, CombatLog>();
    }
    
    public CombatLog getCombatLog(final UUID uuid) {
        return this.combats.containsKey(uuid) ? this.combats.get(uuid) : null;
    }
    
    public void newCombatLog(final UUID damaged, final UUID damager) {
        CombatLog damagedCombatLog = this.combats.get(damaged);
        if (damagedCombatLog == null) {
            damagedCombatLog = this.combats.put(damaged, new CombatLog(damager));
        }
        else {
            damagedCombatLog.hitted(damager);
        }
        CombatLog damagerCombatLog = this.combats.get(damager);
        if (damagerCombatLog == null) {
            damagerCombatLog = this.combats.put(damager, new CombatLog(damaged));
        }
        else {
            damagerCombatLog.hitted(damaged);
        }
    }
    
    public void removeCombatLog(final UUID uuid) {
        final CombatLog combatLog = this.combats.get(uuid);
        if (combatLog == null) {
            return;
        }
        final UUID otherPlayer = combatLog.getCombatLogged();
        final CombatLog otherPlayerCombatLog = this.combats.get(otherPlayer);
        if (otherPlayerCombatLog != null && otherPlayerCombatLog.getCombatLogged() == uuid) {
            this.combats.remove(otherPlayer);
        }
        this.combats.remove(uuid);
    }
}
