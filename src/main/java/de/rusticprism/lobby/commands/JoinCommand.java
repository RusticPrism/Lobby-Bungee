package de.rusticprism.lobby.commands;

import de.rusticprism.lobby.Lobby;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JoinCommand extends Command {
    public JoinCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    if(!(sender instanceof ProxiedPlayer)) {sender.sendMessage(new TextComponent(Lobby.nocons));}
        if(args.length == 1) {
            if(Lobby.plugin.getProxy().getServerInfo(args[0]) == null) {
                sender.sendMessage(new TextComponent(Lobby.prefix + "§cThis Server doesn't exists!"));
                return;
            }
            ProxiedPlayer player = (ProxiedPlayer) sender;
            player.connect(Lobby.plugin.getProxy().getServerInfo(args[0]));
            player.sendMessage(new TextComponent(Lobby.prefix + "§8You got send to " + Lobby.plugin.getProxy().getServerInfo(args[0]).getName()));
        }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cUsage: /join <server>!"));
    }
}
