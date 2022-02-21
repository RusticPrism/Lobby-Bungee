package de.rusticprism.lobby.commands;

import de.rusticprism.lobby.Lobby;
import de.rusticprism.lobby.config.BanConfig;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collections;
import java.util.stream.Collectors;

public class BanCommand extends Command implements TabExecutor {
    public static String reason;
    public static String banmessage = "§8======================================= \n \n §1Ban - §1\n§8Reason: §1"+ reason +"\n §1Ban \n \n§8=======================================";

    public BanCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1) {
            if(Lobby.plugin.getProxy().getPlayer(args[0]).isConnected()) {
                if(BanConfig.isPlayerBanned(BanConfig.getUUid(args[0]))) {
                    sender.sendMessage(new TextComponent(Lobby.prefix + "§cThat Player is already Banned!"));
                    return;
                }
                reason = "";
                for(int i = 1; i < args.length; i++) {
                    reason = reason + args[i]  + " ";
                }
                if(!reason.endsWith("-s")) {
                    reason.replaceAll("-s", "");
                    Lobby.plugin.getProxy().broadcast(new TextComponent(Lobby.prefix + "§8The Player §1") + Lobby.plugin.getProxy().getPlayer(args[0]).getName().toUpperCase() + " got banned by " +sender.getName().toUpperCase() + " for the reason: " + reason.toUpperCase());
                }
                Lobby.plugin.getProxy().getPlayer(args[0]).disconnect(new TextComponent(banmessage.replaceAll("%Player%",sender.getName())));
                BanConfig.BanPlayer( Lobby.plugin.getProxy().getPlayer(args[0]),reason);
                sender.sendMessage(new TextComponent(Lobby.prefix + "§1You banned the Player " + Lobby.plugin.getProxy().getPlayer(args[0]).getName().toUpperCase() + "!"));
            }else sender.sendMessage(new TextComponent(Lobby.prefix + "§c-->" + args[0].toUpperCase() + " is not on the Server!"));
        }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cUsage: /ban <player> <reason>!"));

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if(args.length != 1) {
            return Collections.emptyList();
        }
        return ProxyServer.getInstance().getPlayers().stream()
                .map(ProxiedPlayer::getName)
                .filter(s -> s.startsWith(args[0]))
                .collect(Collectors.toList());
    }
}
