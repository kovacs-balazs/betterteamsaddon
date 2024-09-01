package me.koba1.betterteamsaddon;

import lombok.Getter;
import me.koba1.betterteamsaddon.utils.ConfigUtils;

@Getter
public class ConfigData {

    private String noTeamPlaceholder;
    private int kdrRound;

    public ConfigData() {

        reload();
    }

    public void reload() {
        this.noTeamPlaceholder = ConfigUtils.getOrSet("no_team_placeholder", "-");
        this.kdrRound = ConfigUtils.getOrSet("kdr_round", 2);
    }
}
