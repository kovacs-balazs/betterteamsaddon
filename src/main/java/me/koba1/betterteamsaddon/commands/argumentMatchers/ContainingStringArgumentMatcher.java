package me.koba1.betterteamsaddon.commands.argumentMatchers;

import java.util.List;
import java.util.stream.Collectors;

public class ContainingStringArgumentMatcher implements ArgumentMatcher
{
    @Override
    public List<String> filter (List<String> tabCompletions, String argument)
    {
        return tabCompletions.stream().filter(tabCompletion -> tabCompletion.contains(argument)).collect(Collectors.toList());
    }
}