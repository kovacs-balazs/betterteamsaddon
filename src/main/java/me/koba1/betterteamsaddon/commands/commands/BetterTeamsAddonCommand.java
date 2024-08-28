package me.koba1.betterteamsaddon.commands.commands;

import me.koba1.betterteamsaddon.commands.MainCommand;
import me.koba1.betterteamsaddon.commands.argumentMatchers.ContainingAllCharsOfStringArgumentMatcher;
import me.koba1.betterteamsaddon.commands.subcommands.LookCommand;
import me.koba1.betterteamsaddon.commands.subcommands.ReloadCommand;
import me.koba1.betterteamsaddon.commands.subcommands.SetCommand;
import me.koba1.betterteamsaddon.messages.Message;

public class BetterTeamsAddonCommand extends MainCommand {

    public BetterTeamsAddonCommand() {
        super(Message.NO_PERMISSION.builder().getMessage(), new ContainingAllCharsOfStringArgumentMatcher());
    }

    @Override
    protected void registerSubCommands() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new SetCommand());
        subCommands.add(new LookCommand());
    }
}
