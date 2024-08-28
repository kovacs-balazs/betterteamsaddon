package me.koba1.betterteamsaddon.listeners;

import me.koba1.betterteamsaddon.objects.ITeamHolder;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player)) return;

        Player damager = null;
        if(e.getDamager() instanceof Player) damager = (Player) e.getDamager();
        if(e.getDamager() instanceof Projectile) {
            damager = (Player) ((Projectile) e.getDamager()).getShooter();
        }

        if(damager == null) return;

        ITeamHolder damagerTeam = TeamHolder.getTeamHolder(damager);
        if(damagerTeam == null) return;
        damagerTeam.addDamage(e.getFinalDamage());
    }
}
