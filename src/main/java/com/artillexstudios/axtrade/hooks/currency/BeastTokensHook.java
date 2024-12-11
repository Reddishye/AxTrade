package com.artillexstudios.axtrade.hooks.currency;

import me.mraxetv.beasttokens.api.BeastTokensAPI;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.artillexstudios.axtrade.AxTrade.HOOKS;

public class BeastTokensHook implements CurrencyHook {

    @Override
    public void setup() {
    }

    @Override
    public String getName() {
        return "BeastTokens";
    }

    @Override
    public String getDisplayName() {
        return HOOKS.getString("currencies.BeastTokens.name");
    }

    @Override
    public boolean worksOffline() {
        return true;
    }

    @Override
    public boolean usesDouble() {
        return true;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public double getBalance(@NotNull UUID player) {
        return BeastTokensAPI.getTokensManager().getTokens(Bukkit.getOfflinePlayer(player));
    }

    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID player, double amount) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        BeastTokensAPI.getTokensManager().addTokens(Bukkit.getOfflinePlayer(player), amount);
        cf.complete(true);
        return cf;
    }

    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID player, double amount) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        BeastTokensAPI.getTokensManager().removeTokens(Bukkit.getOfflinePlayer(player), amount);
        cf.complete(true);
        return cf;
    }
}