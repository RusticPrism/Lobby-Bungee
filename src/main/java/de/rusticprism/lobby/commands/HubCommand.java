package de.rusticprism.lobby.commands;

import de.rusticprism.lobby.Lobby;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {
    public HubCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            if(args.length == 0) {
                ServerInfo info = Lobby.plugin.getProxy().getServerInfo("lobby");
                ((ProxiedPlayer) sender).connect(info);
                sender.sendMessage(new TextComponent(Lobby.prefix + "8You got send to the §1Lobby!"));
            }else sender.sendMessage(new TextComponent(Lobby.prefix + "§cPlease use only /hub!"));
        }else sender.sendMessage(new TextComponent(Lobby.nocons));
    }
}
