package me.koba1.betterteamsaddon.utils;

import com.booksaw.betterTeams.Team;
import me.koba1.betterteamsaddon.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {

    public static void broadcastTeam(Team team, Component comp) {
        if(comp == null) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Team playerTeam = Team.getTeam(player);

            if(!team.getID().equals(playerTeam.getID())) continue;

            Main.getInstance().adventure().player(player).sendMessage(comp);
        }
    }
}
