package de.rusticprism.lobby;

import de.rusticprism.lobby.commands.*;
import de.rusticprism.lobby.config.LanguageConfig;
import de.rusticprism.lobby.listeners.JoinQuitListener;
import de.rusticprism.lobby.utils.Config;
import de.rusticprism.lobby.utils.User;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public final class Lobby extends Plugin {

    public static HashMap<UUID, User> USERS = new HashMap<>();
    public static String prefix = "§6Lobby §8>>";
    public static String noperms = prefix + "§cYou dont have the permission to perform that command!";
    public static String nocons = prefix + "§cYou have to be a player to perform that command!";
    public static Lobby plugin;
    @Override
    public void onEnable() {
        plugin = this;
        getProxy().getPluginManager().registerCommand(this,new KickallCommand("kickall","lobby.command.kickall"));
        getProxy().getPluginManager().registerCommand(this, new ServerCommand("server","lobby.command.server"));
        getProxy().getPluginManager().registerCommand(this,new KickCommand("kick","lobby.command.kick"));
        //getProxy().getPluginManager().registerCommand(this,new BanCommand("ban","lobby.command.ban"));
        //getProxy().getPluginManager().registerCommand(this,new UnbanCommand("unban","lobby.command.unban"));
        getProxy().getPluginManager().registerCommand(this,new HubCommand("hub","lobby.command.hub","l", "lobby"));
        getProxy().getPluginManager().registerCommand(this,new JoinCommand("join", "lobby.command.join"));

        getProxy().getPluginManager().registerListener(this,new JoinQuitListener());
        Config.createDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
