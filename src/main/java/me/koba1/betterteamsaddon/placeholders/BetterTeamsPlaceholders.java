package me.koba1.betterteamsaddon.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.koba1.betterteamsaddon.Main;
import me.koba1.betterteamsaddon.objects.ITeamHolder;
import me.koba1.betterteamsaddon.objects.TeamHolder;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BetterTeamsPlaceholders extends PlaceholderExpansion {
    private static final DecimalFormat decimal = new DecimalFormat("0.#");

    @Override
    public @NotNull String getIdentifier() {
        return "betterteamsaddon";
    }

    @Override
    public @NotNull String getAuthor() {
        return "koba1";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        // %betterteamsaddon_kdr%
        // %betterteamsaddon_kdr_<name>%
        // %betterteamsaddon_kills%
        // %betterteamsaddon_kills_<name>%
        // %betterteamsaddon_deaths_<name>%
        // %betterteamsaddon_deaths%
        // %betterteamsaddon_damages_<name>%
        // %betterteamsaddon_damages%

        String[] args = params.split("_");
        ITeamHolder holder = null;
        if (args.length == 1) {
            holder = TeamHolder.getTeamHolder(player);
        } else if (args.length == 2) {
            holder = TeamHolder.getTeamHolder(args[1]);
        }

        if (holder == null) {
            return Main.getInstance().getConfigData().getNoTeamPlaceholder();
        }

        switch (args[0]) {
            case "kills": {
                return holder.getKills() + "";
            }
            case "deaths": {
                return holder.getDeaths() + "";
            }
            case "damages": {
                return decimal.format(holder.getDamages());
            }
            case "kdr": {
                double kdr = (double) holder.getKills() / holder.getDeaths();
                return roundToNDecimalPlaces(kdr, Main.getInstance().getConfigData().getKdrRound()) + "";
            }
        }

        return null;
    }

    private double roundToNDecimalPlaces(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places cannot be negative");

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);  // Rounds using "half up" rounding method
        return bd.doubleValue();
    }
}
