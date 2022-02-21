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

public class UnbanCommand extends Command implements TabExecutor {
    public UnbanCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1) {
            if(BanConfig.isPlayerBanned(BanConfig.getUUid(args[0]))) {
                BanConfig.UnbanPlayer(BanConfig.getUUid(args[0]));
                sender.sendMessage(new TextComponent(Lobby.prefix + "§8Player §1") + args[0] + " §8got unbanned!");
            }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cThis Player is not banned!"));
        }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cUsage: /unban <player>!"));
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
