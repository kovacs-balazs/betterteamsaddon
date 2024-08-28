package me.koba1.betterteamsaddon.listeners;

import me.koba1.betterteamsaddon.objects.TeamHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        TeamHolder.getTeamHolder(e.getPlayer());
    }
}
