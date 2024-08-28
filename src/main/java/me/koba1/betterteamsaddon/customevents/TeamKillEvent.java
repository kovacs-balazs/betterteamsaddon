package me.koba1.betterteamsaddon.customevents;

import lombok.Getter;
import me.koba1.betterteamsaddon.objects.ITeamHolder;
import org.bukkit.entity.Player;

@Getter
public class TeamKillEvent extends TeamEvent {
    private final Player player;
    private final Player killer;

    /**
     * This event fires when a team member kill a player.
     * @param team TeamHolder
     * @param player Victim
     * @param killer Killer
     */
    public TeamKillEvent(ITeamHolder team, Player player, Player killer) {
        super(team);
        this.player = player;
        this.killer = killer;
    }
}
