package me.koba1.betterteamsaddon.listeners;

import com.booksaw.betterTeams.Team;
import me.koba1.betterteamsaddon.customevents.TeamDeathEvent;
import me.koba1.betterteamsaddon.customevents.TeamKillEvent;
import me.koba1.betterteamsaddon.messages.Message;
import me.koba1.betterteamsaddon.objects.ITeamHolder;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import me.koba1.betterteamsaddon.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        ITeamHolder deathTeam = TeamHolder.getTeamHolder(e.getEntity());
        if(deathTeam == null) return;
        deathTeam.addDeaths();

        TeamDeathEvent deathEvent = new TeamDeathEvent(deathTeam, e.getEntity());
        deathEvent.callEvent();

        Team deathBetterTeam = deathTeam.getTeam();
        if(e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller();
            ITeamHolder killerTeam = TeamHolder.getTeamHolder(killer);
            if(killerTeam == null) return;
            killerTeam.addKills();

            TeamKillEvent killEvent = new TeamKillEvent(killerTeam, e.getEntity(), killer);
            killEvent.callEvent();

            Team killerBetterTeam = killerTeam.getTeam();
            Utils.broadcastTeam(killerBetterTeam, Message.TEAM_KILL.builder().setPlayer(e.getEntity()).setKiller(killer).getComponent());
            Utils.broadcastTeam(deathBetterTeam, Message.TEAM_DEATH_KILLER.builder().setPlayer(e.getEntity()).setKiller(killer).getComponent());
        } else {
            Utils.broadcastTeam(deathBetterTeam, Message.TEAM_DEATH.builder().setPlayer(e.getEntity()).getComponent());
        }
    }
}
