package me.koba1.betterteamsaddon;

import com.booksaw.betterTeams.metrics.Metrics;
import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import lombok.Getter;
import lombok.NonNull;
import me.koba1.betterteamsaddon.commands.commands.BetterTeamsAddonCommand;
import me.koba1.betterteamsaddon.files.MessageFile;
import me.koba1.betterteamsaddon.files.TeamData;
import me.koba1.betterteamsaddon.listeners.PlayerDamageListener;
import me.koba1.betterteamsaddon.listeners.PlayerDeathListener;
import me.koba1.betterteamsaddon.listeners.PlayerJoinListener;
import me.koba1.betterteamsaddon.listeners.TeamManageListener;
import me.koba1.betterteamsaddon.messages.ComponentUtil;
import me.koba1.betterteamsaddon.messages.Message;
import me.koba1.betterteamsaddon.objects.ITeamHolder;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import me.koba1.betterteamsaddon.placeholders.BetterTeamsPlaceholders;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public final class Main extends JavaPlugin {

    //private static final String SPIGOT_RESOURCE_ID = "59773";
    @Getter private static Main instance;
    private Map<UUID, ITeamHolder> teams;
    private BetterTeamsPlaceholders placeholders;
    private BukkitAudiences adventure;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.teams = new HashMap<>();

        Metrics metrics = new Metrics(this, 23207);


        if (getServer().getPluginManager().getPlugin("BetterTeams") == null) {
            Bukkit.getLogger().severe("This plugin requires BetterTeams installed on server.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getLogger().severe("This plugin requires PlaceholderAPI installed on server.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.adventure = BukkitAudiences.create(this);

        this.placeholders = new BetterTeamsPlaceholders();
        this.placeholders.register();

        new TeamData("teamdata.yml");
        new MessageFile("messages.yml");

        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new TeamManageListener(), this);

        BetterTeamsAddonCommand cmd = new BetterTeamsAddonCommand();
        cmd.registerMainCommand(this, "betterteamsaddon");

        Message.values();

//        String currentVersion = getDescription().getVersion();
//        new UpdateChecker(this, UpdateCheckSource.SPIGOT, SPIGOT_RESOURCE_ID)
//                .setDownloadLink(SPIGOT_RESOURCE_ID)
//                .onSuccess((senders, latestVersion) -> {
//                    if(latestVersion.equals(currentVersion)) {
//                        return;
//                    }
//                    for (CommandSender sender : senders) {
//                        this.adventure.sender(sender).sendMessage(
//                                Message.PREFIX.builder().getComponent().append(ComponentUtil.formatToComponent("&eNew update available! &6(" + latestVersion + ")"))
//                                        .append(Component.newline())
//                                        .append(ComponentUtil.formatToComponent("&eCurrent version: &e" + currentVersion))
//                                        .append(Component.newline())
//                                        .append(ComponentUtil.formatToComponent("&eDownload link: &6"))
//                        );
//                    }
//                })
//                .onFail((senders, exception) -> {
//                    for (CommandSender sender : senders) {
//                        this.adventure.sender(sender).sendMessage(Message.PREFIX.builder().getComponent().append(ComponentUtil.formatToComponent("<red>Failed check for updates.")));
//                    }
//                })
//                .setNotifyRequesters(false)
//                .checkNow();
    }

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        teams.values().forEach(ITeamHolder::save);

        if(this.placeholders != null) {
            this.placeholders.unregister();
        }
    }
}
