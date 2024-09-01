# BetterTeamsAddon

This plugin is an add-on for BetterTeams that utilizes the BetterTeamsAPI to allow you to track and display various
statistics for each team, such as the number of kills, deaths, and total damage dealt by the team. These features can be
particularly useful for creating leaderboards or enhancing competitive gameplay.

Dependencies: [BetterTeams](https://www.spigotmc.org/resources/better-teams.17129/), [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)

### Features

- MiniMessage support
- RGB support (>1.16)
- Team death and kill broadcast messages
- Admin commands
- Custom team events
- PlaceholderAPI support

### Commands

| Command                                                                           | Permission                      | Description                |
|-----------------------------------------------------------------------------------|---------------------------------|----------------------------|
| /betterteamsaddon reload                                                          | betterteamsaddon.command.reload | Reload configuration files |
| /betterteamsaddon set \<team \| player\> \<kills \| deaths \| damages\> \<value\> | betterteamsaddon.command.set    | Set team's statistic value |
| /betterteamsaddon look \<team \| player\> \<kills \| deaths \| damages\>          | betterteamsaddon.command.look   | Look team's statistics     |

### Placeholders

| Placeholder                         | Description                     |
|-------------------------------------|---------------------------------|
| %betterteamsaddon_kills%            | Returns player's team's kills   |
| %betterteamsaddon_kills_\<team\>%   | Returns team's kills            |
| %betterteamsaddon_deaths%           | Returns player's team's deaths  |
| %betterteamsaddon_deaths_\<team\>%  | Returns team's deaths           |
| %betterteamsaddon_kdr%              | Returns player's team's KDR     |
| %betterteamsaddon_kdr_\<team\>%     | Returns team's KDR              |
| %betterteamsaddon_damages%          | Returns player's team's damages |
| %betterteamsaddon_damages_\<team\>% | Returns team's damages          |

### Developer API

#### Set BetterTeamsAddon as (soft)depend

You need to put BetterTeamsAddon in to your (soft)depend list.

Example:

```yml
name: YourPluginName
version: 1.0.0
author: author
main: your.main.class

softdepend: [ BetterTeamsAddon ]
# or
depend: [ BetterTeamsAddon ]
```

#### Local file
You can import this addon as dependency in to your project with system

```xml
<dependencies>
    <dependency>
        <groupId>me.koba1</groupId>
        <artifactId>betterteamsaddon</artifactId>
        <version>1.0.1</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/BetterTeamsAddon-1.0.1.jar</systemPath>
    </dependency>
</dependencies>
```

#### Maven

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.kovacs-balazs</groupId>
        <artifactId>betterteamsaddon</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```

#### Gradle

```gradle
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.kovacs-balazs:betterteamsaddon:1.0.1'
}
```

#### Examples

```java
import me.koba1.betterteamsaddon.objects.ITeamHolder;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import org.bukkit.entity.Player;

public static int getKills(Player player) {
    ITeamHolder team = TeamHolder.getTeamHolder(player);
    if (team == null) {
        // Player is not in a team.
        return -1;
    }
    return team.getKills();
}
```

```java
import com.booksaw.betterTeams.Team;
import me.koba1.betterteamsaddon.customevents.TeamDeathEvent;

@EventHandler
public void onTeamKill(TeamDeathEvent e) {
    Team team = e.getTeam().getTeam();

    if (team.getMoney() > 15) {
        team.getMoneyComponent().sub(15D);
        // Your team lose 15 money of your bank.
    }
}
```

