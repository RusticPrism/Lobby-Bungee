package de.rusticprism.lobby.listeners;

import de.rusticprism.lobby.Lobby;
import de.rusticprism.lobby.commands.BanCommand;
import de.rusticprism.lobby.config.BanConfig;
import de.rusticprism.lobby.utils.User;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PostLoginEvent e) {
        BanConfig.setUUid(e.getPlayer().getName(),e.getPlayer().getUniqueId().toString());
            if(BanConfig.isPlayerBanned(e.getPlayer().getUniqueId())) {
                e.getPlayer().disconnect(new TextComponent(BanCommand.banmessage.replaceAll("§8Baner: %Player%","You are banned")));
            }
        Lobby.USERS.put(e.getPlayer().getUniqueId(),new User(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e) {
        Lobby.USERS.remove(e.getPlayer().getUniqueId());
    }
}
