package me.koba1.betterteamsaddon.objects;

import com.booksaw.betterTeams.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import me.koba1.betterteamsaddon.Main;
import me.koba1.betterteamsaddon.files.TeamData;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Getter
@AllArgsConstructor
public class TeamHolder implements ITeamHolder {

    private Team team;
    private int kills;
    private int deaths;
    private double damages;

    public TeamHolder(Team team) {
        this(team, 0, 0, 0D);

        Main.getInstance().getTeams().put(this.team.getID(), this);
        load();
    }

    @Override
    public void load() {
        FileConfiguration config = TeamData.getConfig().getFile();
        if(config == null) {
            TeamData.getConfig().setup();
            config = TeamData.getConfig().getFile();
        }

        String path = "teams." + this.team.getID() + ".";
        this.kills = config.getInt(path + "kills", 0);
        this.deaths = config.getInt(path + "deaths", 0);
        this.damages = config.getInt(path + "damages", 0);
        TeamData.getConfig().save();
    }

    @Override
    public void save() {
        FileConfiguration config = TeamData.getConfig().getFile();
        if(config == null) {
            TeamData.getConfig().setup();
            config = TeamData.getConfig().getFile();
        }

        String path = "teams." + this.team.getID() + ".";
        config.set(path + "kills", this.kills);
        config.set(path + "deaths", this.deaths);
        config.set(path + "damages", this.damages);

        TeamData.getConfig().save();
    }

    @Override
    public int setKills(int kills) {
        return this.kills = Math.max(0, kills);
    }

    @Override
    public int setDeaths(int deaths) {
        return this.deaths = Math.max(0, deaths);
    }

    @Override
    public double setDamages(double damage) {
        return this.damages = Math.max(0, damage);
    }

    @Override
    public int addKills() {
        return this.kills += 1;
    }

    @Override
    public int addDeaths() {
        return this.deaths += 1;
    }

    @Override
    public double addDamage(double damage) {
        this.damages += damage;
        if(this.damages > Integer.MAX_VALUE) {
            this.damages = Integer.MAX_VALUE;
            return this.damages;
        }
        return this.damages;
    }

    @NonNull
    public static ITeamHolder getTeamHolder(Team team) {
        if(Main.getInstance().getTeams().containsKey(team.getID())) {
            return Main.getInstance().getTeams().get(team.getID());
        }
        return new TeamHolder(team);
    }

    @Nullable
    public static ITeamHolder getTeamHolder(Player player) {
        Team team = Team.getTeam(player);
        if(team == null) return null;

        return getTeamHolder(team);
    }

    @Nullable
    public static ITeamHolder getTeamHolder(OfflinePlayer player) {
        Team team = Team.getTeam(player);
        if(team == null) return null;

        return getTeamHolder(team);
    }

    @Nullable
    public static ITeamHolder getTeamHolder(String teamName) {
        Team team = Team.getTeam(teamName);
        if(team == null) return null;

        return getTeamHolder(team);
    }
}
