package de.rusticprism.lobby.commands;

import de.rusticprism.lobby.Lobby;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collections;
import java.util.stream.Collectors;

public class KickallCommand extends Command implements TabExecutor {
    private static String reasonall = "§8======================================= \n \n §1KickAll \n \n§8=======================================";
    public KickallCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(new TextComponent(Lobby.prefix + "§cAre you sure you want to kick all player. Please type /kickall confirm to kick all Players from all Server."));
            return;
        }
        if (args[0].equalsIgnoreCase("confirm")) {
            for (ServerInfo server : ProxyServer.getInstance().getServers().values()) {
                server.getPlayers().forEach(proxiedPlayer -> proxiedPlayer.disconnect(reasonall));
            }
            sender.sendMessage(new TextComponent(Lobby.prefix + "§8Kicked all Players from §1all §8Servers!"));
        }else {
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[0]);
        if(serverInfo == null) {
            sender.sendMessage(new TextComponent(Lobby.prefix + "§cThis server doesn't exist!"));
            return;
        }
           serverInfo.getPlayers().forEach(Connection::disconnect);
        sender.sendMessage(new TextComponent(Lobby.prefix + "§8Kicked all Players from the Server:§1'" + serverInfo.getName() + "' §8!"));
        }
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
