package de.rusticprism.lobby.commands;

import de.rusticprism.lobby.Lobby;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collections;
import java.util.stream.Collectors;

public class ServerCommand extends Command implements TabExecutor {
    public ServerCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (args.length != 1) {
                sender.sendMessage(new TextComponent(Lobby.prefix + "§8List of Servers: §1" + ProxyServer.getInstance().getServers() + "§8!"));
                return;
            }
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[0]);
            if (serverInfo == null) {
                sender.sendMessage(new TextComponent(Lobby.prefix + "§cThis server doesn't exist!"));
                return;
            }

            sender.sendMessage(new TextComponent(Lobby.prefix + "§8You joined the Server:§1'" + serverInfo.getName() + "' §8!"));
        }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cYou have to be a Player to perform that Command!"));
    }
    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if(args.length != 1) {
            return Collections.emptyList();
        }
        return ProxyServer.getInstance().getServers().values().stream()
                .map(ServerInfo::getName)
                .filter(s -> s.startsWith(args[0]))
                .collect(Collectors.toList());
    }
}
