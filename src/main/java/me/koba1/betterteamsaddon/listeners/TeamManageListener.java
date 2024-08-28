package me.koba1.betterteamsaddon.listeners;

import com.booksaw.betterTeams.customEvents.CreateTeamEvent;
import com.booksaw.betterTeams.customEvents.DisbandTeamEvent;
import me.koba1.betterteamsaddon.Main;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TeamManageListener implements Listener {

    @EventHandler
    public void onTeamCreate(CreateTeamEvent e) {
        TeamHolder.getTeamHolder(e.getTeam());
    }

    @EventHandler
    public void onTeamDisband(DisbandTeamEvent e) {
        Main.getInstance().getTeams().remove(e.getTeam().getID());
    }
}
